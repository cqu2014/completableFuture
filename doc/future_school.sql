CREATE TABLE `fs_student` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `student_name` varchar(50) NOT NULL DEFAULT '',
  `student_no` varchar(11) NOT NULL DEFAULT '',
  `connection_info` varchar(30) NOT NULL DEFAULT '0' COMMENT '联系方式',
  `entry_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入学时间',
  `grade` int(11) NOT NULL DEFAULT '0' COMMENT '年级:1-6',
  `class_roon` varchar(30) NOT NULL DEFAULT '0' COMMENT '班级',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0.正常, 1.已被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_no` (`student_no`),
  KEY `idx_name` (`student_name`),
  KEY `idx_entry_time` (`entry_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='未来学校学生信息表';

CREATE TABLE `fs_teacher` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(50) NOT NULL DEFAULT '',
  `phone_no` varchar(11) NOT NULL DEFAULT '',
  `position` tinyint(4) NOT NULL DEFAULT '0' COMMENT '职称 0-实习 1-讲师 2-副教授',
  `expertise` varchar(30) DEFAULT NULL COMMENT '擅长领域:语文 数学',
  `entry_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  `teaching_age` int(11) NOT NULL DEFAULT '0' COMMENT '教龄',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0.正常, 1.已被删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_phone_no` (`phone_no`),
  KEY `idx_name` (`teacher_name`),
  KEY `idx_entry_time` (`entry_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='未来学校教师信息表';
