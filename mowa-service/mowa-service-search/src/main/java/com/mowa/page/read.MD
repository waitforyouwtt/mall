 将对象转换成map的方案:
 方法一：Map<String,Object> jsonMap = JSONObject.parseObject(JSON.toJSONString(projectInfo));
 
 
 -- 根据bavatar去重复：
 rightsCouponList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RightsCoupon::getBavatar))), ArrayList::new));
 
 -- 按照RightsCoupon里面的bavatar倒叙排列：
 rightsCouponList.stream().sorted(Comparator.comparing(RightsCoupon::getBavatar).reversed()).collect(Collectors.toList());
 
 -- 取前三条数据 ：
 -- 正序排列：由小到大
 -- 按照RightsCoupon里面的bavatar倒叙排列并且取出钱三条数据
 rightsCouponList.stream().sorted(Comparator.comparing(RightsCoupon::getBavatar).reversed()).limit(3).collect(Collectors.toList());
 
 -- 需要正序排列去掉.reversed()  
 rightsCouponList.stream().sorted(Comparator.comparing(RightsCoupon::getBavatar)).limit(3).collect(Collectors.toList());
 
 -- 获取某个属性List（bavatar）：
 rightsCouponList.stream().map(it -> it.getBavatar()).collect(Collectors.toList());
 
 -- 排序 status顺序 createTime 顺序 
 
 Collections.sort(myRedpackDtoList,Comparator.comparing(MyRedpackDto::getStatus).thenComparing(MyRedpack::getCreateTime));
 
 -- 排序 status降序 createTime 降序
 myRedpackDtoList.stream().sorted(Comparator.comparing(MyRedpackDto::getStatus).reversed().thenComparing(MyRedpack::getCreateTime).reversed());
 
 Collections.sort(myRedpackDtoList,Comparator.comparing(MyRedpackDto::getStatus).reversed().thenComparing(MyRedpack::getCreateTime).reversed());
 
 -- 按指定字段移除
 myRedpackDtoList.removeIf(m -> m.getStatus().contains("a"));
 