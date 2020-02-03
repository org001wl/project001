create table bat_flowpeople
(
  id              varchar(50)  not null
  comment '主键'
    primary key,
  name            varchar(50)  not null
  comment '姓名',
  idcard          varchar(50)  not null
  comment '身份证',
  car_number      varchar(50)  null
  comment '车牌',
  check_time      bigint       null
  comment '检查时间',
  source_address  varchar(200) null
  comment '来源地点',
  target_address  varchar(200) null
  comment '目的地址',
  go_wuhan        bit          not null
  comment '是否去过武汉',
  go_hubei        bit          not null
  comment '是否去过湖北',
  is_fever        bit          not null
  comment '是否发烧',
  check_address   varchar(200) not null
  comment '检查地点',
  check_people    varchar(200) null
  comment '检查人员',
  create_time     bigint       null
  comment '创建时间',
  update_time     bigint       null
  comment '修改时间',
  description     varchar(250) null
  comment '备注',
  other           varchar(250) null
  comment '其他',
  telephone       varchar(50)  null
  comment '手机号',
  account_id      varchar(50)  null
  comment '数据录入账号',
  organization_id varchar(100) null
  comment '数据录入账号所属组织'
)
  comment '流动人员登记表';

