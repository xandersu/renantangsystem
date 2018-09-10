
CREATE TABLE `user` (
  `id` varchar(255) PRIMARY KEY,
  `user_id` varchar(255) ,
  `user_name` varchar(255) NOT NULL ,
  `password` varchar(255) NOT NULL ,
  `create_time` timestamp
)

CREATE TABLE `patient` (
  `id` varchar(255) PRIMARY KEY,
  `name` varchar(255) ,
  `sex` varchar(1) ,
  `phoneNumber` varchar(255)  ,
  `age` varchar(2)  ,
  `address` varchar(255)  ,
  `modify_userid` varchar(255)  ,
  `modify_time` timestamp ,
  `del_flg` varchar(1)
)

CREATE TABLE `case_report` (
  `id` varchar(255) PRIMARY KEY ,
  `patient_id` varchar(255) ,
  `clinic_time` timestamp ,
  `complaint` varchar(255)  ,
  `diagnose` varchar(255)  ,
  `recipe` varchar(255)  ,
  `remark` varchar(255)  ,
  `modify_userid` varchar(255)  ,
  `modify_time` timestamp ,
  `del_flg` varchar(1)
)