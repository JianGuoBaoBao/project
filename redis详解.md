##redis详解
> 解压到指定目录

[https://redis.io/commands/][redis-commands]

`tar -zxf 压缩包 -C 目录`
`tar -zxf redis-7.0.11.tar.gz -C redis`
`make`

> redis 默认安装目录：/usr/local/bin
```
   [root@iZbp1dd2mju7p5655e1bgrZ redis-7.0.11]# cd /usr
   [root@iZbp1dd2mju7p5655e1bgrZ usr]# ls
   bin  etc  games  include  lib  lib64  libexec  local  sbin  share  src  tmp
   [root@iZbp1dd2mju7p5655e1bgrZ usr]# cd local
   [root@iZbp1dd2mju7p5655e1bgrZ local]# ls
   aegis  bin  etc  games  include  lib  lib64  libexec  sbin  share  src  sysak
   [root@iZbp1dd2mju7p5655e1bgrZ local]# cd bin
   [root@iZbp1dd2mju7p5655e1bgrZ bin]# ls
   redis-benchmark  redis-check-aof  redis-check-rdb  redis-cli  redis-sentinel  redis-server
   [root@iZbp1dd2mju7p5655e1bgrZ bin]# 

```

> 拷贝redis.config 文件
```
    [root@iZbp1dd2mju7p5655e1bgrZ usr]# cd /usr/local/bin
    [root@iZbp1dd2mju7p5655e1bgrZ bin]# cp /root/redis/redis-7.0.11/redis.conf kconfig
    [root@iZbp1dd2mju7p5655e1bgrZ bin]# cd kconfig

```

> 指定conf启动（后台启动）
```
[root@iZbp1dd2mju7p5655e1bgrZ bin]# redis-server kconfig/redis.conf
20966:C 27 Jun 2023 16:39:21.674 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
20966:C 27 Jun 2023 16:39:21.674 # Redis version=7.0.11, bits=64, commit=00000000, modified=0, pid=20966, just started
20966:C 27 Jun 2023 16:39:21.674 # Configuration loaded
20966:M 27 Jun 2023 16:39:21.675 * monotonic clock: POSIX clock_gettime
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 7.0.11 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                  
 (    '      ,       .-`  | `,    )     Running in standalone mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 6379
 |    `-._   `._    /     _.-'    |     PID: 20966
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           https://redis.io       
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               


```
> [root@iZbp1dd2mju7p5655e1bgrZ bin]# redis-server kconfig/redis.conf &   后台运行不影响当前其他操作
> [root@iZbp1dd2mju7p5655e1bgrZ bin]# redis-server kconfig/redis.conf 
> [root@iZbp1dd2mju7p5655e1bgrZ bin]# redis-cli -p 6379

```
 查看redis是否运行
[root@iZbp1dd2mju7p5655e1bgrZ bin]# ps -ef|grep redis
   root     20997 16300  0 16:44 pts/0    00:00:00 redis-server 127.0.0.1:6379
   root     21008 16300  0 16:47 pts/0    00:00:00 grep --color=auto redis

```
> redis 默认有16个数据库
> redis-benchmark 压力测试工具


> redis命令
```
   flushdb 清除当前数据库
   flushall 清除全部数据库的内容
   select 0 切换数据库
   select 3

   DEBSIZE # 查看DB大小

   set Key1  v1
   get Key1

   EXIST Key1
   
   APPEND Key1 "hello"  


```

###Redis5大数据类型 Redis-Key

####String

```
    127.0.0.1:6379> get name
    "kuangshenniub"
    127.0.0.1:6379> APPEND name "honghong"
    (integer) 21
    127.0.0.1:6379> get name
    "kuangshenniubhonghong"
    127.0.0.1:6379> 


    浏览量： incr 增加浏览量 decr 减去浏览量
    INCRBY: 添加量     DECRBY: 减少量
    127.0.0.1:6379> set views 0
    OK
    127.0.0.1:6379> get views
    "0"
    127.0.0.1:6379> incr views
    (integer) 1
    127.0.0.1:6379> incr views
    (integer) 2
    127.0.0.1:6379> get views
    "2"
    127.0.0.1:6379> decr views
    (integer) 1
    127.0.0.1:6379> 

    127.0.0.1:6379> INCRBY VIEWS 10
    (integer) 10
    127.0.0.1:6379> DECRBY VIEWS 5
    (integer) 5
    127.0.0.1:6379> 

```

> 字符串截取
```
    127.0.0.1:6379> set key1 "hello, kuangshen"
    OK
    127.0.0.1:6379> get key1
    "hello, kuangshen"
    127.0.0.1:6379> GETRANGE key1 0 3
    "hell"
    127.0.0.1:6379> GETRANGE key1 0 -1
    "hello, kuangshen"
```

> 字符串替换
```
    127.0.0.1:6379> set key2 abcdefg
    OK
    127.0.0.1:6379> setrange key2 1 xx
    (integer) 7
    127.0.0.1:6379> get key2
    "axxdefg"

```

> 为key设置时效
----------------------------------------------------------------

setex (set with expire)  #给对象设置时效
setnx (set if not exist) #不存在再设置，不存在则设置，存在则设置失败。

mset k v k1 v1 ... #批量设置键值对
mget k1 k2 k3 .. #批量获取值

```
    127.0.0.1:6379> setex key3 30 "hello"
    OK
    127.0.0.1:6379> get key3
    "hello"
    127.0.0.1:6379> get key3
    "hello"
    127.0.0.1:6379> get key3
    (nil)
    127.0.0.1:6379> 

    127.0.0.1:6379> setnx mykey "redis"
    (integer) 1
    127.0.0.1:6379> keys *
    1) "name"
    2) "VIEWS"
    3) "mykey"
    4) "key1"
    5) "views"
    6) "key2"

    
    127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3
    OK
    127.0.0.1:6379> keys *
    1) "k2"
    2) "k1"
    3) "k3"
    127.0.0.1:6379> 

    127.0.0.1:6379> mget k1 k2 k3
    1) "v1"
    2) "v2"
    3) "v3"


    # k4并没有操作成功：#msetnx 是一个原子性操作，要么一起成功，要么一起失败
    127.0.0.1:6379> msetnx k1 v1 k4 v4
    (integer) 0
    127.0.0.1:6379> keys *
    1) "k2"
    2) "k1"
    3) "k3"
    127.0.0.1:6379> 


    #对象
    #这里的key是一个巧妙的设计：  user:{id}:{filed}  入参在Redis中设置
    set user:1 (name:zhangsan, age:3) # 设置一个user:1对象值为json字符串保存一个对象
    127.0.0.1:6379> mset user:1:name zhangsan user:1:age 2
    OK
    127.0.0.1:6379> mget user:1:name user:1:age
    1) "zhangsan"
    2) "2"
    127.0.0.1:6379> 

```


----------------------------------------------------------------

## db创建

> getset ->如果存在则获取，没有则创建

```
        127.0.0.1:6379> getset db redis
        (nil)
        127.0.0.1:6379> get db
        "redis"
        127.0.0.1:6379> getset db mongodb
        "redis"
        127.0.0.1:6379> get db
        "mongodb"
        127.0.0.1:6379> 

```

## List

> 所有的list命令都是l开头的

```
   127.0.0.1:6379> LPUSH list one
   (integer) 1
   127.0.0.1:6379> LPUSH list two
   (integer) 2
   127.0.0.1:6379> LPUSH list three
   (integer) 3
    127.0.0.1:6379> LRANGE list 0 -1
    1) "three"
    2) "two"
    3) "one"
    127.0.0.1:6379> LRANGE list 0 1
    1) "three"
    2) "two"


    127.0.0.1:6379> LRANGE list 0 -1
    1) "three"
    2) "two"
    3) "one"
    4) "righr1"
    127.0.0.1:6379> 

    #移除左边list的第一个元素
    127.0.0.1:6379> Lpop list
    "three"
    127.0.0.1:6379> LRANGE list 0 -1
    1) "two"
    2) "one"
    3) "righr1"

    #移除右边最后一个元素
    127.0.0.1:6379> Rpop list
    "righr1"
    127.0.0.1:6379> LRANGE list 0 -1
    1) "two"
    2) "one"

     #获取list下标
     127.0.0.1:6379> lindex list 1
     "one"
     127.0.0.1:6379> lindex list 3
     (nil)

    #获取list长度
     127.0.0.1:6379> llen list
     (integer) 2

    #移除元素lrem 
    127.0.0.1:6379> lrange list 0 -1
    1) "two"
    2) "three"
    3) "three"
    4) "three"
    5) "two"
    6) "one"
    127.0.0.1:6379> lrem list 2 two
    (integer) 2
    127.0.0.1:6379> lrange list 0 -1
    1) "three"
    2) "three"
    3) "three"
    4) "one"

    
    #ltrim裁剪 会改变原数组
    127.0.0.1:6379> Rpush mylist hello hello1 hello2 hello3
    (integer) 4
    127.0.0.1:6379> lrange mylist 0 -1
    1) "hello"
    2) "hello1"
    3) "hello2"
    4) "hello3"
    127.0.0.1:6379> ltrim mylist 1 2
    OK
    127.0.0.1:6379> lrange mylist 0 -1
    1) "hello1"
    2) "hello2"


    #rpoplpush :组合命令将原来的元素移动到其他数组中

    127.0.0.1:6379> rpush mylist hello hello1 hello2 hello3
    (integer) 4
    127.0.0.1:6379> rpoplpush mylist myotherlist
    "hello3"
    127.0.0.1:6379> lrange mylist 0 -1
    1) "hello"
    2) "hello1"
    3) "hello2"
    127.0.0.1:6379> lrange myotherlist 0 -1
    1) "hello3"


    #lset 更新list里面的值替换为另外一个值，更新操作   
    127.0.0.1:6379> lpush list value1
    (integer) 1
    127.0.0.1:6379> lrange list 0 0 
    1) "value1"
    127.0.0.1:6379> lset list 0 item #如果存在替换当前下标的值，
    OK
    127.0.0.1:6379> lrange list 0 0 
    1) "item"

    127.0.0.1:6379> lset list 2 item #如果下标的值不存在则报错，
    (error) ERR index out of range
    127.0.0.1:6379> 


    #将摸个具体的value插入到列表某个元素的签名或者后面！
    127.0.0.1:6379> Rpush mylist hello
    (integer) 1
    127.0.0.1:6379> rpush mylist world
    (integer) 2
    127.0.0.1:6379> linsert mylist before world other
    (integer) 3
    127.0.0.1:6379> lrange mylist 0 -1
    1) "hello"
    2) "other"
    3) "world"
    127.0.0.1:6379> linsert mylist after world new 
    (integer) 4
    127.0.0.1:6379> lrange mylist 0 -1
    1) "hello"
    2) "other"
    3) "world"
    4) "new"

    # before Node after , left right 都可以插入值
    # 如果key 不存在， 创建新的链表
    # 如果key 存在，新增内容
    # 插入中间位置效率会更低， 两边速度快
```

##Set(集合)
> 无序不重复集合
> sadd 添加元素  smembers 查看元素  sismember 是否存在元素
```
    127.0.0.1:6379> sadd myset hello
    (integer) 1
    127.0.0.1:6379> sadd myset kuangshen
    (integer) 1
    127.0.0.1:6379> sadd myset lovekuangshen
    (integer) 1
    127.0.0.1:6379> smembers myset  # 获取set集合中的内容元素个数
    1) "lovekuangshen"
    2) "hello"
    3) "kuangshen"
    127.0.0.1:6379> sismember myset hello
    (integer) 1
    127.0.0.1:6379> sismember myset world
    (integer) 0

    # 随机抽取
    127.0.0.1:6379> srandmember myset
    "lovekuangshen"
    127.0.0.1:6379> srandmember myset
    "kuangshen"
    127.0.0.1:6379> srandmember myset
    "kuangshen"
    127.0.0.1:6379> srandmember myset
    "lovekuangshen"

    # 随机弹出
    127.0.0.1:6379> smembers myset
    1) "lovekuangshen"
    2) "hello"
    3) "kuangshen"
    127.0.0.1:6379> spop myset
    "hello"
    127.0.0.1:6379> smembers myset
    1) "lovekuangshen"
    2) "kuangshen"
    127.0.0.1:6379> 

    # 将一个指定的值移动到其他set中
    127.0.0.1:6379> smembers myset
    1) "kuangshen"
    2) "kuangshen2"
    3) "world"
    4) "lovekuangshen"
    5) "hello"
    127.0.0.1:6379> sadd myset2 "set2"
    (integer) 1
    127.0.0.1:6379> smove myset myset2 kuangshen2
    (integer) 1
    127.0.0.1:6379> smembers myset2
    1) "kuangshen2"
    2) "set2"

    
    SDIFF KEY1 KEY2  #差集
    SINTER KEY1 KEY2 #交集
    Sunion KEY1 KEY2 #并集
    
    微博，A用户将所有关注的人放到一个set中！
```

##HASH(哈希)
> Map集合， key-map 时候这个值是一个map集合

```
    127.0.0.1:6379> hset myhash field1 kuangshen
    (integer) 1
    127.0.0.1:6379> hget myhash field1
    "kuangshen"

    127.0.0.1:6379> hmset myhash field1 hello field2 world
    OK
    127.0.0.1:6379> hmget myhash field1 field2
    1) "hello"
    2) "world"

    hexists myhash field1 # 判断是否存在key field

    hkeys myhash   获取map集合中所有的field
    hvals myhash   获取所有value

    hsetnx myhash field4 hello  如果存在则不能设置
```

## Zset(有序集合)
> 在set基础上，增加了一个值

```
    127.0.0.1:6379> zadd myset 1 one
    (integer) 1
    127.0.0.1:6379> zadd myset 2 two
    (integer) 1
    127.0.0.1:6379> zadd myset 3 three
    (integer) 1
    127.0.0.1:6379> zrange myset 0 -1
    1) "one"
    2) "two"
    3) "three"

    #
    127.0.0.1:6379> zadd salary 2500 xiaohong 
    (integer) 1
    127.0.0.1:6379> zadd salary 5000 zhangsan
    (integer) 1
    127.0.0.1:6379> zadd salary 500 kuangshen
    (integer) 1
    127.0.0.1:6379> zrangebyscore salary -inf +inf
    1) "kuangshen"
    2) "xiaohong"
    3) "zhangsan"

    127.0.0.1:6379> zrevrange salary 0 -1
    1) "zhangsan"
    2) "kuangshen"

    127.0.0.1:6379> zrangebyscore salary -inf 2500 withscores
    1) "kuangshen"
    2) "500"
    3) "xiaohong"
    4) "2500"

    127.0.0.1:6379> zrange salary 0 -1
    1) "kuangshen"
    2) "xiaohong"
    3) "zhangsan"
    127.0.0.1:6379> zrem salary xiaohong


    127.0.0.1:6379> zrem salary xiaohong
    (integer) 1
    127.0.0.1:6379> zcard salary       #获取有序集合中的个数
    (integer) 2


    
    127.0.0.1:6379> zadd myset 1 hello
    (integer) 1
    127.0.0.1:6379> zadd myset 2 world 3 kuangshen
    (integer) 2
    127.0.0.1:6379> zcount myset 1 3  #获取指定区间的成员数量
    (integer) 3
    127.0.0.1:6379> zcount myset 1 2
    (integer) 2


    

```

##Geospatial地理位置详解

##Hyperloglog基数统计

##Bitmap位图


##Redis事务
> Redis事务本质：一组命令的集合！一个事务中的所有命名都会被序列化，在事务执行过程中，会按照顺序执行。
> 一次性、顺序性、排他性！执行一些列的命令！

------ 队列 set set set 执行 ------

Redis事务没有隔离级别的概念！
所有的命令在事务中，并没有直接被执行！只有发起执行命令的时候才会执行！Exec

Redis单条命令式保存原子性，但是事务不保证原子性！

redis的事务：
- 开启事务（multi）
- 命令入队（...）
- 执行事务（exec）

```
    127.0.0.1:6379> multi
    OK
    127.0.0.1:6379(TX)> set k1 v1
    QUEUED
    127.0.0.1:6379(TX)> set k2 v2
    QUEUED
    127.0.0.1:6379(TX)> get k2
    QUEUED
    127.0.0.1:6379(TX)> set k3 v3
    QUEUED
    127.0.0.1:6379(TX)> exec
    1) OK
    2) OK
    3) "v2"
    4) OK

```

> 放弃事务

`discard  #取消事务，中间入队的命令都不会被执行`

> 编译型异常（代码有问题！命令有错！！！），事务中所有的命令都不会被执行！


> 运行时异常（1/0）,如果事务队列中存在语法性，你们执行命令的时候，其他命令可以正常执行的，错误命令抛出异常

  

##Redis监控 watch

> 悲观锁
- 很悲观，认为什么时候都会出问题，无论做什么都会加锁

> 乐观锁
- 认为什么时候都不会出问题，所以不会上锁！更新数据的时候去判断一下，在此期间是否有人修改过这个数据。
- 获取version
- 更新的时候比较version

> Redis测监视测试
- 测试多线程修改值， 使用watch可以当做redis的乐观锁操作！

1.watch money 监视
2.multi
...任务队列
3.exec  #如果执行失败了就要重新unwatch之后重新watch
unwatch money 放弃监视


##Jedis
> java连接的开发工具的中间件

```
    

```
















