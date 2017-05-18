## secondary sort: 二次排序
二次排序是指在归约阶段对某个键关联的值进行排序。
mapreduce基本范式：
map(key1, value1) -> list(key2, value2)
reduce(key2, list(value2)) -> list(key3, value3)
其中reducer接收的值list(value2)是无序的。
二次排序目标就是让reducer接收的值有某种顺序。

#### 解决方案
 第一种方案是让归约器读取和缓存给定键的所有值，然后对这些值完成一个归约器中排序。这种方法不具伸缩性，容易导致内容溢出。
 第二种方案是使用mapreduce框架对归约器值排序，这种方法会为自然键增加部分或整个值来创建一个组合键来完成排序目标。
具体如下：
 1. 使用键值转换设计模式，构造一个组合中间键（K,V1),其中V1 是次键，K为自然键。
 2. 让mapreduce执行框架完成排序
 3. 保留多个键－值对的状态来完成处理。

#### 日期温度二次排序代码目录
DateTemperaturePair: 定义组合键
DateTemperaturePartitioner: 定制分区器
DateTemperatureGroupingComparator: 分组比较器
SecondarySortMapper: map函数
SecondarySortReducer: reduce函数
SecondarySortDriver: 启动器类
