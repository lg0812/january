use january;

insert into goodsinfo(id,active,createDate,creator,goodsLogoPath,goodsName,goodsNo,goodsType,purchaseDesc,remark,shareDesc,tags)
values (1,1,"2017-9-24 12:00:00","admin","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","goodsName",
"no","aaa","desc","详细内容","fenxiang","wu");

insert into goodsinfo(id,active,createDate,creator,goodsLogoPath,goodsName,goodsNo,goodsType,purchaseDesc,remark,shareDesc,tags)
values (2,1,"2017-9-24 12:00:00","admin","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","goodsName",
"no","aaa","desc","详细内容","fenxiang","wu");

insert into goodsinfo(id,active,createDate,creator,goodsLogoPath,goodsName,goodsNo,goodsType,purchaseDesc,remark,shareDesc,tags)
values (3,1,"2017-9-24 12:00:00","admin","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","goodsName",
"no","aaa","desc","详细内容","fenxiang","wu");

insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (1,10,100,11,1);
insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (2,10,100,11,1);
insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (3,10,100,11,1);
insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (4,10,100,11,2);
insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (5,10,100,11,2);


insert into Recommend (id,recommendGoodsId,goods_id) values (1,2,1);

insert into Enshrine (id,creator,goods_id) values (1,"admin",1);
insert into Enshrine (id,creator,goods_id) values (2,"admin",2);

insert into GoodsTag (id,tagName,tag_id) values (1,"admin",null);
insert into GoodsTag (id,tagName,tag_id) values (2,"admin",1);
insert into GoodsTag (id,tagName,tag_id) values (3,"admin",1);
insert into GoodsTag (id,tagName,tag_id) values (4,"admin",null);
insert into GoodsTag (id,tagName,tag_id) values (5,"admin",4);
insert into GoodsTag (id,tagName,tag_id) values (6,"admin",4);

insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply,goods_id) values (2,"comment","LG0812","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","2017-12-24 12:00:00","感谢买家好评",1);
insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply,goods_id) values (1,"comment","LG0812","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","2017-9-24 12:00:00","感谢买家好评",1);
insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply,goods_id) values (3,"comment","LG0812","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","2017-11-24 12:00:00","感谢买家好评",1);
insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply,goods_id) values (4,"comment","LG0812","http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg","2017-8-24 12:00:00","感谢买家好评",1);

insert into CloudPath (id,filePath,goods_id) values (1,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",1);
insert into CloudPath (id,filePath,goods_id) values (2,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",1);
insert into CloudPath (id,filePath,goods_id) values (3,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",1);

insert into CloudPath (id,filePath,goods_id) values (4,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",2);
insert into CloudPath (id,filePath,goods_id) values (5,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",2);
insert into CloudPath (id,filePath,goods_id) values (6,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",2);
insert into CloudPath (id,filePath,comment_id) values (7,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",1);
insert into CloudPath (id,filePath,comment_id) values (8,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",1);
insert into CloudPath (id,filePath,comment_id) values (9,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",2);
insert into CloudPath (id,filePath,comment_id) values (10,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",2);
insert into CloudPath (id,filePath,comment_id) values (11,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",2);
insert into CloudPath (id,filePath,comment_id) values (12,"http://www.yltfy.cn/january/compress/286ed4ea-909a-48be-81f6-d0b83921a87blogo.jpg",3);