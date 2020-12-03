/*
Navicat MySQL Data Transfer

Source Server         : mall_goods
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : mall_content

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-12-03 22:13:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL COMMENT '内容类目ID',
  `title` varchar(255) DEFAULT NULL COMMENT '内容标题',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `status` int(11) DEFAULT NULL COMMENT '状态：0无效 1 有效',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(20) DEFAULT NULL,
  `update_by` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('1', '1', '麻球', 'www.baidu.com', 'www.baidu.com', '0', '1', '云澜哥哥', '云澜哥哥', '2020-12-03 22:09:04', '2020-12-03 22:09:04');
INSERT INTO `tb_content` VALUES ('2', '1', '麻花', 'www.jd.com', 'www.jd.com', '0', '2', '云澜哥哥', '云澜哥哥', '2020-12-03 22:09:04', '2020-12-03 22:09:04');
INSERT INTO `tb_content` VALUES ('3', '2', '铁观音', 'www.taobao.com', 'www.taobao.com', '0', '3', '云澜', '云澜', '2020-12-03 22:10:55', '2020-12-03 22:10:55');
INSERT INTO `tb_content` VALUES ('4', '2', '碧螺春', 'www.taobao.com', 'www.taobao.com', '0', '4', '凤凰', '凤凰', '2020-12-03 22:11:54', '2020-12-03 22:11:54');
INSERT INTO `tb_content` VALUES ('5', '2', '大红袍', 'www.jd.com', 'www.jd.com', '0', '5', '凤凰', '凤凰', '2020-12-03 22:12:24', '2020-12-03 22:12:24');

-- ----------------------------
-- Table structure for tb_content_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_content_category`;
CREATE TABLE `tb_content_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content_category
-- ----------------------------
INSERT INTO `tb_content_category` VALUES ('1', '食品');
INSERT INTO `tb_content_category` VALUES ('2', '茶叶');
INSERT INTO `tb_content_category` VALUES ('3', '特产');
