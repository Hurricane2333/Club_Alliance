/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80041
 Source Host           : localhost:3306
 Source Schema         : club_alliance

 Target Server Type    : MySQL
 Target Server Version : 80041
 File Encoding         : 65001

 Date: 26/06/2025 16:57:07
*/

CREATE DATABASE IF NOT EXISTS `club_alliance` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `club_alliance`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_participant
-- ----------------------------
DROP TABLE IF EXISTS `activity_participant`;
CREATE TABLE `activity_participant`  (
  `participation_id` int NOT NULL AUTO_INCREMENT COMMENT '参与id',
  `activity_id` int NOT NULL COMMENT '活动id',
  `user_id` int NOT NULL COMMENT '参与人id',
  `status` enum('PENDING','APPROVED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING' COMMENT '参与状态',
  `apply_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '参与时间',
  `review_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`participation_id`) USING BTREE,
  UNIQUE INDEX `user_activity_unique`(`user_id` ASC, `activity_id` ASC) USING BTREE,
  INDEX `activity_id`(`activity_id` ASC) USING BTREE,
  CONSTRAINT `activity_participant_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `club_activity` (`activity_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `activity_participant_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_participant
-- ----------------------------

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announce_id` int NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `user_id` int NOT NULL COMMENT '发布人id',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '公告创建时间',
  PRIMARY KEY (`announce_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------

-- ----------------------------
-- Table structure for club
-- ----------------------------
DROP TABLE IF EXISTS `club`;
CREATE TABLE `club`  (
  `club_id` int NOT NULL AUTO_INCREMENT COMMENT '社团id',
  `club_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团名字',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团简介',
  `category` enum('ACADEMIC','ARTS','SPORTS','VOLUNTEER','HOBBY','CAREER','DEBATE','MAKER','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团类别',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团图标URL',
  `president_id` int NOT NULL COMMENT '负责人ID',
  `requirements` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '加入条件',
  `favorite_count` int NULL DEFAULT 0 COMMENT '收藏人数',
  `current_members` int NULL DEFAULT 0 COMMENT '现有人数',
  `status` enum('PENDING','APPROVED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING' COMMENT '审核状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '社团创建时间',
  PRIMARY KEY (`club_id`) USING BTREE,
  UNIQUE INDEX `club_name`(`club_name` ASC) USING BTREE,
  INDEX `president_id`(`president_id` ASC) USING BTREE,
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`president_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club
-- ----------------------------
INSERT INTO `club` VALUES (1, '艺术社', '搞艺术的', 'ARTS', NULL, 1, '会搞艺术的', 0, 2, 'APPROVED', '2025-06-26 16:36:02');
INSERT INTO `club` VALUES (2, '学术社', '搞学术的', 'ACADEMIC', NULL, 3, '会搞学术的', 1, 1, 'APPROVED', '2025-06-26 16:37:12');

-- ----------------------------
-- Table structure for club_activity
-- ----------------------------
DROP TABLE IF EXISTS `club_activity`;
CREATE TABLE `club_activity`  (
  `activity_id` int NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `club_id` int NOT NULL COMMENT '社团id',
  `user_id` int NOT NULL COMMENT '发布人id',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动内容',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动地点',
  `status` enum('PENDING','ACTIVE','CONCLUDED','CANCELLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING' COMMENT '活动状态',
  `current_participants` int NULL DEFAULT 0 COMMENT '活动参与人数',
  `max_participants` int NULL DEFAULT NULL COMMENT '活动人数上限',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动创建时间',
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `club_id`(`club_id` ASC) USING BTREE,
  CONSTRAINT `club_activity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `club_activity_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club_activity
-- ----------------------------

-- ----------------------------
-- Table structure for club_member
-- ----------------------------
DROP TABLE IF EXISTS `club_member`;
CREATE TABLE `club_member`  (
  `relation_id` int NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `user_id` int NOT NULL COMMENT '用户id',
  `club_id` int NOT NULL COMMENT '社团id',
  `status` enum('PENDING','APPROVED','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING' COMMENT '关系状态',
  `apply_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '申请原因',
  `apply_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `review_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `reject_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '拒绝原因',
  PRIMARY KEY (`relation_id`) USING BTREE,
  UNIQUE INDEX `user_club_unique`(`user_id` ASC, `club_id` ASC) USING BTREE,
  INDEX `club_id`(`club_id` ASC) USING BTREE,
  CONSTRAINT `club_member_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `club_member_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club_member
-- ----------------------------
INSERT INTO `club_member` VALUES (1, 1, 1, 'APPROVED', 'president', '2025-06-26 16:36:02', '2025-06-26 16:36:02', NULL);
INSERT INTO `club_member` VALUES (2, 3, 2, 'APPROVED', 'president', '2025-06-26 16:37:12', '2025-06-26 16:37:12', NULL);
INSERT INTO `club_member` VALUES (3, 2, 1, 'APPROVED', '2', '2025-06-26 16:49:19', '2025-06-26 16:49:19', NULL);

-- ----------------------------
-- Table structure for club_post
-- ----------------------------
DROP TABLE IF EXISTS `club_post`;
CREATE TABLE `club_post`  (
  `post_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `club_id` int NOT NULL COMMENT '社团id',
  `user_id` int NOT NULL COMMENT '发布人id',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子内容',
  `visibility` enum('PUBLIC','MEMBERS','PRESIDENT') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'MEMBERS' COMMENT '帖子可见性',
  `status` enum('ACTIVE','BLOCKED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'ACTIVE' COMMENT '帖子状态',
  `comment_count` int NULL DEFAULT 0 COMMENT '评论数量',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帖子创建时间',
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '帖子更新时间',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `club_id`(`club_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `club_post_ibfk_1` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `club_post_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of club_post
-- ----------------------------

-- ----------------------------
-- Table structure for favorite_club
-- ----------------------------
DROP TABLE IF EXISTS `favorite_club`;
CREATE TABLE `favorite_club`  (
  `favorite_id` int NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` int NOT NULL COMMENT '用户id',
  `club_id` int NOT NULL COMMENT '社团id',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏创建时间',
  PRIMARY KEY (`favorite_id`) USING BTREE,
  UNIQUE INDEX `user_club_fav`(`user_id` ASC, `club_id` ASC) USING BTREE,
  INDEX `club_id`(`club_id` ASC) USING BTREE,
  CONSTRAINT `favorite_club_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `favorite_club_ibfk_2` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite_club
-- ----------------------------
INSERT INTO `favorite_club` VALUES (1, 2, 2, '2025-06-26 16:50:41');

-- ----------------------------
-- Table structure for post_comment
-- ----------------------------
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment`  (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `post_id` int NOT NULL COMMENT '帖子id',
  `user_id` int NOT NULL COMMENT '发布人id',
  `parent_id` int NULL DEFAULT NULL COMMENT '父评论id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `status` enum('ACTIVE','BLOCKED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'ACTIVE' COMMENT '评论状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `post_id`(`post_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `parent_id`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `club_post` (`post_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `post_comment_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_comment
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `stu_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户学号',
  `stu_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像URL',
  `is_admin` tinyint(1) NULL DEFAULT 0 COMMENT '是否是管理员',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `stu_id`(`stu_id` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '111', '一一一', '111', '111@111.com', NULL, 1, '2025-06-26 16:26:38');
INSERT INTO `user` VALUES (2, '222', '二二二', '222', '222@222.com', NULL, 1, '2025-06-26 16:27:21');
INSERT INTO `user` VALUES (3, '333', '三三三', '333', '333@333.com', NULL, 0, '2025-06-26 16:27:36');
INSERT INTO `user` VALUES (4, '444', '四四四', '444', '444@444.com', NULL, 0, '2025-06-26 16:27:49');
INSERT INTO `user` VALUES (5, '555', '五五五', '555', '555@555.com', NULL, 0, '2025-06-26 16:53:43');
INSERT INTO `user` VALUES (6, '666', '六六六', '666', '666@666.com', NULL, 0, '2025-06-26 16:54:29');

-- ----------------------------
-- Triggers structure for table activity_participant
-- ----------------------------
DROP TRIGGER IF EXISTS `after_participant_update`;
delimiter ;;
CREATE TRIGGER `after_participant_update` AFTER UPDATE ON `activity_participant` FOR EACH ROW BEGIN
    IF NEW.status = 'approved' AND OLD.status != 'approved' THEN
        UPDATE club_activity
        SET current_participants = current_participants + 1
        WHERE activity_id = NEW.activity_id;
    END IF;

    IF OLD.status = 'approved' AND NEW.status != 'approved' THEN
        UPDATE club_activity
        SET current_participants = current_participants - 1
        WHERE activity_id = NEW.activity_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table activity_participant
-- ----------------------------
DROP TRIGGER IF EXISTS `after_participant_delete`;
delimiter ;;
CREATE TRIGGER `after_participant_delete` AFTER DELETE ON `activity_participant` FOR EACH ROW BEGIN
    IF OLD.status = 'approved' THEN
        UPDATE club_activity
        SET current_participants = current_participants - 1
        WHERE activity_id = OLD.activity_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table club_member
-- ----------------------------
DROP TRIGGER IF EXISTS `after_member_status_update`;
delimiter ;;
CREATE TRIGGER `after_member_status_update` AFTER UPDATE ON `club_member` FOR EACH ROW BEGIN
    IF NEW.status = 'approved' AND OLD.status != 'approved' THEN
        UPDATE club
        SET current_members = current_members + 1
        WHERE club_id = NEW.club_id;
    END IF;

    IF OLD.status = 'approved' AND NEW.status != 'approved' THEN
        UPDATE club
        SET current_members = current_members - 1
        WHERE club_id = NEW.club_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table club_member
-- ----------------------------
DROP TRIGGER IF EXISTS `after_member_delete`;
delimiter ;;
CREATE TRIGGER `after_member_delete` AFTER DELETE ON `club_member` FOR EACH ROW BEGIN
    IF OLD.status = 'approved' THEN
        UPDATE club
        SET current_members = current_members - 1
        WHERE club_id = OLD.club_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table favorite_club
-- ----------------------------
DROP TRIGGER IF EXISTS `after_favorite_insert`;
delimiter ;;
CREATE TRIGGER `after_favorite_insert` AFTER INSERT ON `favorite_club` FOR EACH ROW BEGIN
    UPDATE club
    SET favorite_count = favorite_count + 1
    WHERE club_id = NEW.club_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table favorite_club
-- ----------------------------
DROP TRIGGER IF EXISTS `after_favorite_delete`;
delimiter ;;
CREATE TRIGGER `after_favorite_delete` AFTER DELETE ON `favorite_club` FOR EACH ROW BEGIN
    UPDATE club
    SET favorite_count = favorite_count - 1
    WHERE club_id = OLD.club_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table post_comment
-- ----------------------------
DROP TRIGGER IF EXISTS `after_comment_insert`;
delimiter ;;
CREATE TRIGGER `after_comment_insert` AFTER INSERT ON `post_comment` FOR EACH ROW BEGIN
    UPDATE club_post
    SET comment_count = comment_count + 1
    WHERE post_id = NEW.post_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table post_comment
-- ----------------------------
DROP TRIGGER IF EXISTS `after_comment_delete`;
delimiter ;;
CREATE TRIGGER `after_comment_delete` AFTER DELETE ON `post_comment` FOR EACH ROW BEGIN
    UPDATE club_post
    SET comment_count = comment_count - 1
    WHERE post_id = OLD.post_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
