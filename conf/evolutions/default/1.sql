# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table agency (
  id                            integer auto_increment not null,
  agency_name                   varchar(255),
  distribution_channel_id       integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_agency primary key (id)
);

create table booker (
  id                            integer auto_increment not null,
  user_id                       bigint,
  agency_id                     integer not null,
  distribution_channel_id       integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_booker primary key (id)
);

create table distribution_channel (
  id                            integer auto_increment not null,
  distribution_name             varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_distribution_channel primary key (id)
);

create table element (
  id                            integer auto_increment not null,
  name                          varchar(255),
  atomic_number                 integer not null,
  symbol                        varchar(255),
  metal_group                   varchar(255),
  constraint pk_element primary key (id)
);

create table element_sub (
  id                            integer auto_increment not null,
  name                          varchar(255),
  atomic_number                 integer not null,
  symbol                        varchar(255),
  metal_group                   varchar(255),
  constraint pk_element_sub primary key (id)
);

create table email_history (
  id                            bigint auto_increment not null,
  order_id                      bigint,
  event_id                      bigint,
  order_status                  integer not null,
  email_status                  integer not null,
  email_time                    datetime(6),
  constraint pk_email_history primary key (id)
);

create table event (
  id                            bigint auto_increment not null,
  name_vi                       varchar(255),
  name_en                       varchar(255),
  event_type                    varchar(255),
  link                          varchar(255),
  address_vi                    varchar(255),
  address_en                    varchar(255),
  location                      varchar(255),
  venue_vi                      varchar(255),
  venue_en                      varchar(255),
  banner_vi                     varchar(255),
  banner_en                     varchar(255),
  poster_vi                     varchar(255),
  poster_en                     varchar(255),
  sit_map                       varchar(255),
  date_show                     bigint,
  enable                        tinyint(1) default 0,
  description_vi                varchar(5000),
  description_en                varchar(5000),
  email_contact                 varchar(255),
  phone_contact                 varchar(255),
  list_organizer                varchar(1000),
  list_condition                varchar(500),
  time_remind_event             integer not null,
  time_remind_payment           integer not null,
  time_cancel_payment           integer not null,
  time_remind_payment_payoo_store integer not null,
  time_cancel_payment_payoo_store integer not null,
  time_remind_payment_office    integer not null,
  time_cancel_payment_office    integer not null,
  is_dual_language              integer not null,
  is_send_email                 integer not null,
  is_send_email_remind          integer not null,
  is_allow_checkin              integer not null,
  is_send_email_remind_event    integer not null,
  constraint pk_event primary key (id)
);

create table event_condition (
  id                            integer auto_increment not null,
  event_id                      bigint,
  id_condition                  integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_condition primary key (id)
);

create table event_gift (
  id                            integer auto_increment not null,
  event_id                      bigint,
  name                          varchar(255),
  image                         varchar(255),
  description                   varchar(255),
  number                        integer not null,
  used                          integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_gift primary key (id)
);

create table event_group_voucher (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  type                          integer not null,
  name                          varchar(255),
  note                          varchar(255),
  number                        integer not null,
  number_min_ticket             integer not null,
  discount_value                integer not null,
  min_revenue_order             integer not null,
  max_discount                  integer not null,
  code_group                    varchar(255),
  gen_code_type                 integer not null,
  revenue_discount_type         integer not null,
  list_gift                     varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_event_group_voucher primary key (id)
);

create table event_offer_discount (
  id                            integer auto_increment not null,
  event_id                      bigint,
  offer_discount                integer not null,
  agency_id                     integer not null,
  distribution_channel_id       integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_offer_discount primary key (id)
);

create table event_order (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  gender                        integer not null,
  name                          varchar(255),
  phone                         varchar(255),
  email                         varchar(255),
  company_name                  varchar(255),
  company_address               varchar(255),
  company_tax_code              varchar(255),
  city                          varchar(255),
  district                      varchar(255),
  ward                          varchar(255),
  address                       varchar(255),
  buyer_user_id                 bigint,
  note                          varchar(255),
  pay_type                      integer not null,
  apply_voucher                 varchar(255),
  revenue_before                integer not null,
  revenue_discount              integer not null,
  revenue_after                 integer not null,
  ticket_info                   varchar(5000),
  time_create                   bigint,
  invoice_vat                   tinyint(1) default 0,
  status                        integer not null,
  time_payment_success          bigint,
  time_send_email               varchar(255),
  language_code                 integer not null,
  constraint pk_event_order primary key (id)
);

create table event_order_gift (
  id                            integer auto_increment not null,
  event_id                      bigint,
  id_order                      bigint,
  id_gift                       integer not null,
  number                        integer not null,
  time_create                   bigint,
  status                        integer not null,
  constraint pk_event_order_gift primary key (id)
);

create table event_organizer (
  id                            integer auto_increment not null,
  event_id                      bigint,
  name                          varchar(255),
  image                         varchar(255),
  enable                        tinyint(1) default 0,
  description                   varchar(5000),
  constraint pk_event_organizer primary key (id)
);

create table event_payment_method (
  id                            integer auto_increment not null,
  event_id                      bigint,
  id_payment_method             integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_payment_method primary key (id)
);

create table event_payment_office_address (
  id                            integer auto_increment not null,
  event_id                      bigint,
  address                       varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_event_payment_office_address primary key (id)
);

create table event_refund_condition (
  id                            integer auto_increment not null,
  event_id                      bigint,
  is_refund                     tinyint(1) default 0,
  refund_after_day              integer not null,
  refund_condition              varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_event_refund_condition primary key (id)
);

create table event_require_info (
  id                            integer auto_increment not null,
  event_id                      bigint,
  index_sort                    integer not null,
  question_vi                   varchar(255),
  question_en                   varchar(255),
  answer_type                   varchar(255),
  required                      tinyint(1) default 0,
  enable                        tinyint(1) default 0,
  constraint pk_event_require_info primary key (id)
);

create table event_require_info_answer (
  id                            integer auto_increment not null,
  event_id                      bigint,
  order_id                      bigint,
  event_require_info_id         integer not null,
  answer                        varchar(255),
  constraint pk_event_require_info_answer primary key (id)
);

create table event_require_info_detail (
  id                            integer auto_increment not null,
  event_require_info_id         integer not null,
  index_sort                    integer not null,
  value                         varchar(255),
  text_vi                       varchar(255),
  text_en                       varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_event_require_info_detail primary key (id)
);

create table event_schedule (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  date                          varchar(255),
  time                          varchar(255),
  start                         bigint,
  end                           bigint,
  time_show                     bigint,
  title                         varchar(255),
  note                          varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_event_schedule primary key (id)
);

create table event_schedule_ticket_type (
  id                            integer auto_increment not null,
  id_schedule                   integer not null,
  id_ticket_type                bigint,
  create_time                   bigint,
  action_user                   integer not null,
  note                          varchar(255),
  enable                        tinyint(1) default 0,
  number_ticket_sold            integer not null,
  constraint pk_event_schedule_ticket_type primary key (id)
);

create table event_ticket_type (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  name_vi                       varchar(255),
  name_en                       varchar(255),
  total                         integer not null,
  price                         bigint,
  min_buy                       integer not null,
  max_buy                       integer not null,
  start                         bigint,
  end                           bigint,
  start_date                    varchar(255),
  end_date                      varchar(255),
  start_time                    varchar(255),
  end_time                      varchar(255),
  type_ticket                   varchar(255),
  description_vi                varchar(10000),
  description_en                varchar(10000),
  note                          varchar(255),
  stop_ticketing                tinyint(1) default 0,
  only_payment_office           tinyint(1) default 0,
  schedule_text                 varchar(255),
  enable                        tinyint(1) default 0,
  total_ticket_sold             integer not null,
  constraint pk_event_ticket_type primary key (id)
);

create table event_type (
  id                            integer auto_increment not null,
  name                          varchar(255),
  note                          varchar(255),
  constraint pk_event_type primary key (id)
);

create table event_voucher (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  order_id                      bigint,
  group_voucher_id              bigint,
  code                          varchar(255),
  used                          tinyint(1) default 0,
  enable                        tinyint(1) default 0,
  constraint pk_event_voucher primary key (id)
);

create table event_voucher_condition (
  id                            integer auto_increment not null,
  group_voucher_id              bigint,
  ticket_type_id                bigint,
  schedule_id                   bigint,
  number                        integer not null,
  start                         bigint,
  end                           bigint,
  enable                        tinyint(1) default 0,
  constraint pk_event_voucher_condition primary key (id)
);

create table event_voucher_get_ticket (
  id                            integer auto_increment not null,
  group_voucher_id              bigint,
  ticket_type_id                bigint,
  schedule_id                   bigint,
  number                        integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_voucher_get_ticket primary key (id)
);

create table event_voucher_gift (
  id                            integer auto_increment not null,
  id_voucher                    bigint,
  id_gift                       integer not null,
  number                        integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_voucher_gift primary key (id)
);

create table event_voucher_ticket (
  id                            integer auto_increment not null,
  id_voucher                    bigint,
  id_ticket_type                integer not null,
  id_schedule                   integer not null,
  number                        integer not null,
  type_discount                 varchar(255),
  discount                      integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_event_voucher_ticket primary key (id)
);

create table list_condition (
  id                            integer auto_increment not null,
  image                         varchar(255),
  description                   varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_list_condition primary key (id)
);

create table payment_method (
  id                            integer auto_increment not null,
  name                          varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_payment_method primary key (id)
);

create table permission (
  id                            integer auto_increment not null,
  event_id                      bigint,
  user_id                       bigint,
  permission_group_id           integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_permission primary key (id)
);

create table permission_action_list (
  id                            integer auto_increment not null,
  action_name                   varchar(255),
  enable                        tinyint(1) default 0,
  constraint pk_permission_action_list primary key (id)
);

create table permission_list (
  id                            integer auto_increment not null,
  permission_name               varchar(255),
  time_create                   varchar(255),
  enable                        tinyint(1) default 0,
  note                          varchar(255),
  constraint uq_permission_list_permission_name unique (permission_name),
  constraint pk_permission_list primary key (id)
);

create table permission_user_action (
  id                            integer auto_increment not null,
  permission_id                 integer not null,
  action_id                     integer not null,
  enable                        tinyint(1) default 0,
  constraint pk_permission_user_action primary key (id)
);

create table product (
  product_id                    integer auto_increment not null,
  name                          varchar(255),
  image                         varchar(255),
  href                          varchar(255),
  price                         integer not null,
  belong_cate_level1_id         integer not null,
  belong_cate_level2_id         integer not null,
  belong_cate_level3_id         integer not null,
  enable                        integer not null,
  constraint pk_product primary key (product_id)
);

create table product_sale (
  product_id                    integer auto_increment not null,
  name                          varchar(255),
  image                         varchar(255),
  href                          varchar(255),
  price                         integer not null,
  belong_cate_level1_id         integer not null,
  belong_cate_level2_id         integer not null,
  belong_cate_level3_id         integer not null,
  enable                        integer not null,
  constraint pk_product_sale primary key (product_id)
);

create table product_trending (
  product_id                    integer auto_increment not null,
  name                          varchar(255),
  image                         varchar(255),
  href                          varchar(255),
  price                         integer not null,
  belong_cate_level1_id         integer not null,
  belong_cate_level2_id         integer not null,
  belong_cate_level3_id         integer not null,
  enable                        integer not null,
  constraint pk_product_trending primary key (product_id)
);

create table relationship (
  id                            integer auto_increment not null,
  full_name                     varchar(255),
  user_name                     varchar(255),
  email                         varchar(255),
  phone_number                  varchar(255),
  password                      varchar(255),
  gender                        varchar(255),
  birthday                      varchar(255),
  permission_user               varchar(255),
  time_create                   bigint,
  action_user                   varchar(255),
  note                          varchar(255),
  constraint pk_relationship primary key (id)
);

create table slide_banner (
  id                            integer auto_increment not null,
  event_id                      bigint,
  time_create                   bigint,
  type                          integer not null,
  date_from                     bigint,
  date_to                       bigint,
  enable                        tinyint(1) default 0,
  constraint pk_slide_banner primary key (id)
);

create table status (
  id                            integer auto_increment not null,
  value                         integer not null,
  content                       varchar(255),
  type                          varchar(255),
  note                          varchar(255),
  constraint pk_status primary key (id)
);

create table ticket (
  id                            bigint auto_increment not null,
  event_id                      bigint,
  id_order                      bigint,
  id_ticket_type                bigint,
  id_schedule                   bigint,
  id_schedule_ticket_type       integer not null,
  revenue_before                integer not null,
  revenue_discount              integer not null,
  revenue_after                 integer not null,
  time_create                   bigint,
  status                        integer not null,
  checkin_status                integer not null,
  checkin_info                  varchar(255),
  note                          varchar(255),
  seat                          varchar(255),
  constraint pk_ticket primary key (id)
);

create table token (
  id                            integer auto_increment not null,
  phone                         varchar(255),
  token                         varchar(255),
  device                        varchar(255),
  time_create                   bigint,
  enable                        tinyint(1) default 0,
  constraint pk_token primary key (id)
);

create table user (
  user_id                       bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_user primary key (user_id)
);

create table user_product_log (
  user_id                       varchar(255) not null,
  product_id                    integer not null,
  belong_cate_level1_id         integer not null,
  belong_cate_level1_name       varchar(255),
  belong_cate_level2_id         integer not null,
  belong_cate_level2_name       varchar(255),
  belong_cate_level3_id         integer not null,
  belong_cate_level3_name       varchar(255),
  time_create                   bigint,
  constraint pk_user_product_log primary key (user_id)
);

create table user_search_keyword (
  user_id                       varchar(255) not null,
  keyword                       varchar(255),
  time_create                   bigint,
  constraint pk_user_search_keyword primary key (user_id)
);


# --- !Downs

drop table if exists agency;

drop table if exists booker;

drop table if exists distribution_channel;

drop table if exists element;

drop table if exists element_sub;

drop table if exists email_history;

drop table if exists event;

drop table if exists event_condition;

drop table if exists event_gift;

drop table if exists event_group_voucher;

drop table if exists event_offer_discount;

drop table if exists event_order;

drop table if exists event_order_gift;

drop table if exists event_organizer;

drop table if exists event_payment_method;

drop table if exists event_payment_office_address;

drop table if exists event_refund_condition;

drop table if exists event_require_info;

drop table if exists event_require_info_answer;

drop table if exists event_require_info_detail;

drop table if exists event_schedule;

drop table if exists event_schedule_ticket_type;

drop table if exists event_ticket_type;

drop table if exists event_type;

drop table if exists event_voucher;

drop table if exists event_voucher_condition;

drop table if exists event_voucher_get_ticket;

drop table if exists event_voucher_gift;

drop table if exists event_voucher_ticket;

drop table if exists list_condition;

drop table if exists payment_method;

drop table if exists permission;

drop table if exists permission_action_list;

drop table if exists permission_list;

drop table if exists permission_user_action;

drop table if exists product;

drop table if exists product_sale;

drop table if exists product_trending;

drop table if exists relationship;

drop table if exists slide_banner;

drop table if exists status;

drop table if exists ticket;

drop table if exists token;

drop table if exists user;

drop table if exists user_product_log;

drop table if exists user_search_keyword;

