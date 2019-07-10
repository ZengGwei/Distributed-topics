### Java NIO 三件套
 在 NIO 中有几个核心对象需要掌握：缓冲区（Buffer）、选择器（Selector）、通道（Channel）。

#### 1.缓冲区 - Buffer
缓冲区实际是一个容器对象，（数组），在NIO 中所有数据都市通过
     缓冲区处理。而 面向流I/O系统中，所有数据直接写入或读取到stream 对象中。
+ Buffer 有三个重要属性：
    1. position 新建时 为0 ，flip() 时 置为0，get()/put()方法更新
    2. limit 指定还有多少数据 初始时 为capacity ，flip()时 为 position值
    3. capacity : 指定可存最大数据大小，及数组的大小。
    
+ 缓冲区的分配： .allocate(n) 静态方法。或包装一个数组 .wrap(new byte[n])
+ 子缓冲区：与服缓冲区数据共享
+ 只读缓冲区  buffer.asReadOnlyBuffer();
+ 直接缓冲区：
+ 内存映射：
#### 2. 选择器 Selector
   NIO 非阻塞IO采用了基于reactor 模式的工作方式，I/O调用不会被阻塞，注册 事件。
   核心对象就是Selector ,Selector 就是注册各种I/O 事件的地方。
### 3 . 通道 Channel   
   通道是一个对象，通过通道可以读取或写入数据，通道链接的事缓冲区buffer.
   NIO中提供了多种通道（ServerSocketChannel、SocketChannel、DatagramChannel、FileChannel），所有通道实现了Channel接口。

   目前流行的多路复用IO实现有四种：select、poll、epoll、kqueue
    
### 4. NIO 源码    