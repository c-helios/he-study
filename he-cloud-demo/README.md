

# 安装zipkin
docker run --name zipkin  -d -p 9411:9411 openzipkin/zipkin

# 安装nacos

1. 单机模式

docker run -d -p 8848:8848 -v /Users/hcw/docker/nacos/conf/application.properties:/home/nacos/conf/application.properties -v /Users/hcw/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties -v /Users/hcw/docker/nacos/logs/nacos1:/home/nacos/logs --name nacos1 nacos/nacos-server:v2.0.4-slim
或
docker run -d -p 8848:8848 --env-file /Users/hcw/docker/nacos/env/nacos-hostname.env -v /Users/hcw/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties -v /Users/hcw/docker/nacos/logs/nacos1:/home/nacos/logs --name nacos1 nacos/nacos-server:v2.0.4-slim

2.集群模式

docker run -d -p 8848:8848 --env-file /Users/hcw/docker/nacos/env/nacos.env -v /Users/hcw/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties -v /Users/hcw/docker/nacos/logs/nacos1:/home/nacos/logs --name nacos1 nacos/nacos-server:v2.0.4-slim
docker run -d -p 8849:8848 --env-file /Users/hcw/docker/nacos/env/nacos.env -v /Users/hcw/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties -v /Users/hcw/docker/nacos/logs/nacos2:/home/nacos/logs --name nacos2 nacos/nacos-server:v2.0.4-slim
docker run -d -p 8850:8848 --env-file /Users/hcw/docker/nacos/env/nacos.env -v /Users/hcw/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties -v /Users/hcw/docker/nacos/logs/nacos3:/home/nacos/logs --name nacos3 nacos/nacos-server:v2.0.4-slim


# 安装sentinel
1. 拉取镜像
   docker pull bladex/sentinel-dashboard
2. 启动容器
   docker run --name sentinel -d -p 8858:8858 -p 8719:8719 -d -e AUTH_USERNAME=sentinel -e AUTH_PASSWORD=sentinel -e NACOS_SERVER_ADDR=192.168.31.29:8848 -e NACOS_USERNAME=nacos -e NACOS_PASSWORD=nacos -e NACOS_NAMESPACE=public -e NACOS_GROUP_ID=SENTINEL_GROUP bladex/sentinel-dashboard

# 安装seata
docker run --name seata  -d -p 8091:8091 -p 7091:7091 -e SEATA_IP=192.168.31.29 -v /Users/hcw/docker/seata/config:/seata-server/resources seataio/seata-server:1.5.0

将config.txt配置到nacos中
cd ${SEATAPATH}/script/config-center/nacos/
sh nacos-config.sh -h localhost -p 8848 -g SEATA_GROUP -t 5a3c7d6c-f497-4d68-a71a-2e5e3340b3ca -u username -w password

# seata sql

create database seata CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;
use seata;
CREATE TABLE `branch_table` (
`branch_id` bigint(20) NOT NULL,
`xid` varchar(128) NOT NULL,
`transaction_id` bigint(20) DEFAULT NULL,
`resource_group_id` varchar(32) DEFAULT NULL,
`resource_id` varchar(256) DEFAULT NULL,
`branch_type` varchar(8) DEFAULT NULL,
`status` tinyint(4) DEFAULT NULL,
`client_id` varchar(64) DEFAULT NULL,
`application_data` varchar(2000) DEFAULT NULL,
`gmt_create` datetime(6) DEFAULT NULL,
`gmt_modified` datetime(6) DEFAULT NULL,
PRIMARY KEY (`branch_id`),
KEY `idx_xid` (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `distributed_lock` (
`lock_key` char(20) NOT NULL,
`lock_value` varchar(20) NOT NULL,
`expire` bigint(20) DEFAULT NULL,
PRIMARY KEY (`lock_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `global_table` (
`xid` varchar(128) NOT NULL,
`transaction_id` bigint(20) DEFAULT NULL,
`status` tinyint(4) NOT NULL,
`application_id` varchar(32) DEFAULT NULL,
`transaction_service_group` varchar(32) DEFAULT NULL,
`transaction_name` varchar(128) DEFAULT NULL,
`timeout` int(11) DEFAULT NULL,
`begin_time` bigint(20) DEFAULT NULL,
`application_data` varchar(2000) DEFAULT NULL,
`gmt_create` datetime DEFAULT NULL,
`gmt_modified` datetime DEFAULT NULL,
PRIMARY KEY (`xid`),
KEY `idx_status_gmt_modified` (`status`,`gmt_modified`),
KEY `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `lock_table` (
`row_key` varchar(128) NOT NULL,
`xid` varchar(128) DEFAULT NULL,
`transaction_id` bigint(20) DEFAULT NULL,
`branch_id` bigint(20) NOT NULL,
`resource_id` varchar(256) DEFAULT NULL,
`table_name` varchar(32) DEFAULT NULL,
`pk` varchar(36) DEFAULT NULL,
`status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:locked ,1:rollbacking',
`gmt_create` datetime DEFAULT NULL,
`gmt_modified` datetime DEFAULT NULL,
PRIMARY KEY (`row_key`),
KEY `idx_status` (`status`),
KEY `idx_branch_id` (`branch_id`),
KEY `idx_xid_and_branch_id` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


# account sql

create database seata_account CHARSET=utf8 COLLATE utf8_general_ci;
use seata_account;

CREATE TABLE `t_account` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
`total` decimal(11,0) DEFAULT NULL COMMENT '总额度',
`used` decimal(11,0) DEFAULT NULL COMMENT '已用额度',
`residue` decimal(11,0) DEFAULT NULL COMMENT '剩余额度',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `undo_log` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`branch_id` bigint(20) NOT NULL,
`xid` varchar(100) NOT NULL,
`context` varchar(128) NOT NULL,
`rollback_info` longblob NOT NULL,
`log_status` int(11) NOT NULL,
`log_created` datetime NOT NULL,
`log_modified` datetime NOT NULL,
`ext` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

# storage sql

create database seata_storage CHARSET=utf8 COLLATE utf8_general_ci;
use seata_storage;

CREATE TABLE `t_storage` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
`total` int(11) DEFAULT NULL COMMENT '总库存',
`used` int(11) DEFAULT NULL COMMENT '已用库存',
`residue` int(11) DEFAULT NULL COMMENT '剩余库存',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `undo_log` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`branch_id` bigint(20) NOT NULL,
`xid` varchar(100) NOT NULL,
`context` varchar(128) NOT NULL,
`rollback_info` longblob NOT NULL,
`log_status` int(11) NOT NULL,
`log_created` datetime NOT NULL,
`log_modified` datetime NOT NULL,
`ext` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

# order sql

create database seata_order CHARSET=utf8 COLLATE utf8_general_ci;
use seata_order;
CREATE TABLE `t_order` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
`product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
`count` int(11) DEFAULT NULL COMMENT '数量',
`money` decimal(11,0) DEFAULT NULL COMMENT '金额',
`status` int(1) DEFAULT NULL COMMENT '订单状态：0:创建中 1:已完结',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


CREATE TABLE `undo_log` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`branch_id` bigint(20) NOT NULL,
`xid` varchar(100) NOT NULL,
`context` varchar(128) NOT NULL,
`rollback_info` longblob NOT NULL,
`log_status` int(11) NOT NULL,
`log_created` datetime NOT NULL,
`log_modified` datetime NOT NULL,
`ext` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;



# dubbo 调用会出现问题：No provider available from ...
解决方案：
https://github.com/alibaba/spring-cloud-alibaba/issues/2024

# 安装说明
# 北极星单机版包含以下4个组件：

polaris-console：可视化控制台，提供服务治理管控页面
polaris-server：控制面，提供数据面组件及控制台所需的后台接口
prometheus：服务治理监控所需的指标汇聚统计组件
pushgateway：prometheus推送网关，支持数据面通过推送方式上报监控数据到prometheus
北极星单机版默认占用以下端口：

polaris-console：8080(http/tcp)
polaris-server：8090(http/tcp，注册中心端口)、8091(grpc/tcp，注册中心端口)、8093(grpc/tcp，配置中心端口)
prometheus：9090(tcp)
pushgateway：9091(tcp)
#


# 安装腾讯北极星 polaris 

## polaris server
1、先部署一个临时的，拷贝出配置文件再删除重新部署
docker run -d --name polaris-server polarismesh/polaris-server-standalone:v1.10.0
2、拷贝出配置文件
docker cp polaris-server:/root/polaris-server.yaml /Users/hcw/docker/polaris/polaris-server.yaml
3、删除
docker stop polaris-server && docker rm polaris-server
4、重新部署
docker run -d --name polaris-server -h polaris-server -p 8090:8090 -p 8091:8091 -p 8093:8093 -p 8761:8761 -p 15010:15010 -v /Users/hcw/docker/polaris/polaris-server.yaml:/root/polaris-server.yaml polarismesh/polaris-server-standalone:v1.10.0

## polaris console
1、先部署一个临时的，拷贝出配置文件再删除重新部署
docker run -d --name polaris-console polarismesh/polaris-console:v1.7.0
2、拷贝出配置文件
docker cp polaris-console:/root/polaris-console.yaml /Users/hcw/docker/polaris/polaris-console.yaml
3、删除
docker stop polaris-console && docker rm polaris-console
4、重新部署
docker run -d --name polaris-console -p 5080:8080 -v /Users/hcw/docker/polaris/polaris-console.yaml:/root/polaris-console.yaml polarismesh/polaris-console:v1.7.0

