create database if not exists shape;

use shape;

create table IF NOT EXISTS tb_shape (
    id int unsigned primary key auto_increment comment '主键ID',
		shape varchar(4) not null comment '图形',
    X int not null comment '起始点x坐标',
		Y int not null comment '起始点y坐标',
		WIDTH int not null comment '图形的宽',
		HIEGHT int not null comment '图形的高'
) comment '图形绘画';

insert into tb_shape values(1,'圆形',100,100,180,200);
insert into tb_shape values(2,'矩形',300,300,450,400);
insert into tb_shape values(3,'直线',800,100,600,700);