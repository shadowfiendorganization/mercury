CREATE TABLE diary_list(
  id int(16) auto_increment PRIMARY key comment'主键',
  title VARCHAR(8) DEFAULT null comment'用户名',
  content varchar(2048) NOT NULL comment'日志内容',
  create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment'上传时间',
  category_name varchar(20) DEFAULT NULL comment'日志类型',
  category_id INTEGER (8) DEFAULT NULL comment'类型ID'
)charset='utf8'