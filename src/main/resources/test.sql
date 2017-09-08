use january;
select * from goodsinfo;
--update usertoken set accessToken = "abc" where accessTokenId = 1;
--update usertoken set accessToken = "abc" where accessTokenId = 1;


insert into goodsinfo(id,active,createDate,creator,goodsLogoPath,goodsName,goodsNo,goodsType,purchaseDesc,remark,shareDesc,tags)
values (1,1,"2017-9-24 12:00:00","admin","http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg","goodsName",
"no","aaa","desc","详细内容","fenxiang","wu");

insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (1,10,100,11,1);
insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (2,10,100,11,1);
insert into goodsspec(id,retailPrice ,salesvolume,stock,goodsid) values (3,10,100,11,1);

insert into previewpics (id,picturepath,goodsid) values (1,"http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg",1);
insert into previewpics (id,picturepath,goodsid) values (2,"http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg",1);
insert into previewpics (id,picturepath,goodsid) values (3,"http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg",1);

insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply) values (1,"comment","LG0812","http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg","2017-9-24 12:00:00","感谢买家好评");
insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply) values (2,"comment","LG0812","http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg","2017-12-24 12:00:00","感谢买家好评");
insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply) values (3,"comment","LG0812","http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg","2017-11-24 12:00:00","感谢买家好评");
insert into Comment (id,comment,userName,userLogo,createDate,shopkeeperReply) values (4,"comment","LG0812","http://www.yltfy.cn/january/compress/a03723b7-c4ce-436f-acee-6ae39e183d0blogo.jpg","2017-8-24 12:00:00","感谢买家好评");
