### 1.NIO
>所有缓冲区都有4个属性：capacity、limit、position、mark，并遵循：mark <= position <= limit <= capacity

Capacity:容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变
Limit:表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的
Position:位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备
Mark:标记，调用mark()来设置mark=position，再调用reset()可以让position恢复到标记的位置

### 2.clear、flip、rewind、compact
clear()，只是把指针移到位置0，并没有真正清空数据。 
flip()，当前位置设置为EOF，指针指向0. 
rewind，指针指向0. 
compact()，压缩数据。比如当前EOF是6，当前指针指向2 
（即0,1的数据已经写出了，没用了）， 
那么compact方法将把2,3,4,5的数据挪到0,1,2,3的位置， 
然后指针指向4的位置。这样的意思是，从4的位置接着再写入数据。

