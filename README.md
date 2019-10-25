# online-shop

Android课程设计（很简陋，但已有功能都已完善）

## 使用语言

Java

## 数据存储

sqlite

## 主要页面

### 登录界面

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/1.png)

[^图1 登录界面]: 此图是网上药店的登录界面。用户和管理员进入系统必须要进行登录。在输入账号和密码之后点击登录按钮进行登录，点击注册按钮进行注册，点击忘记密码按钮，进入忘记密码界面。

### 注册界面

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/2.png)

[^图2 注册界面]: 在输入注册账号、输入密码、确认密码之后点击确定按钮，执行注册命令，点击取消按钮，返回登录界面。

### 用户界面 

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/3.png)

[^图3 主页界面]: 该页面是用户登录后显示的界面，主页默认显示首页界面，点击上方的查询商品输入商品关键字和进行模糊查询显示在查询界面中。点击我的按钮可进入我的界面。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/4.png)

[^图4 我的界面]: 该页面为点击我的按钮后显示的页面。用户名后显示当前登录者的用户名，下方是其身份，左面是默认头像。点击我的订单后进入我的订单界面，点击意见反馈后弹出对话框进行意见反馈。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/5.png)

[^图5 查询界面]: 该界面是在输入框中输入关键字进行查询后的界面。该图示意的结果为输入“b”的结果。图片为商品图，右侧上方为商品名，下方为价格，最右侧为详情按钮，点击后可进入商品详情页。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/6.png)

[^图6 商品详情页]: 该页面为点击详情按钮后的界面。点击加入购物车按钮后即可购买。下方为商品评价，显示用户名和评价内容。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/7.png)

[^图7 我的订单界面]: 该图为点击我的订单后的界面。点击最右侧评价即可添加评价。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/8.png)

[^图8 反馈界面]: 该图为点击意见反馈后的界面。输入反馈后点击确定即可添加反馈。

### 管理员界面

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/9.png)

[^图9 管理员界面]: 该图为管理员登录后的界面。点击用户管理可以进行用户管理，点击商品管理可以进行用商品户管理，点击商品留言可以查看用户留言，点击网站留言可以查看网站留言。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/10.png)

[^图10 用户管理界面]: 该图为点击用户管理后的界面。三个显示内容分别为：用户ID、用户名、用户密码。点击删除按钮之后可删除该用户。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/11.png)

[^图11 商品管理界面]: 该图是点击商品管理后的的界面。显示的几个参数分别为：商品ID、商品图片、商品名、价格。点击右侧删除按钮后可将该行数据删除。点击上方添加按钮可添加药品。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/12.png)

[^图12 添加药品界面]: 该页面为点击添加按钮后的界面。输入药品名和价格后点击确认按钮，进行商品添加。（商品图片目前默认）

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/13.png)

[^图13 商品评价界面/空界面]: 该图是点击商品评价按钮后的界面。由于系统中还没有用户进行过商品评价，故调用了空界面。

![img](https://github.com/ryukieinn/online-shop.git/raw/master/img/14.png)

[^图14 留言管理界面]: 该界面是点击留言管理后的界面。显示用户反馈，显示参数分别为用户名和反馈内容。

## 特别说明

本项目是移动应用开发课程的课程设计，开始到完成时间不到一周，本人水平又有限，因此有很多不足，比如：

1. 购物车功能没有实现
2. 管理员未存入数据库而是写到了代码中定死
3. 页面传参由于涉及Activity与Fragment之间通信，水平不够没能实现，未使用intent + bundle进行传参而是写了静态数据类来进行赋值调用
4. listview有些显示直接页面居中
5. 首页只做了轮播图和查询，下方本来想做listview显示部分药品，最后时间原因没写

不过这个项目足够我答辩了，以后有时间有精力再来修复。

## 感谢

这个项目参考许多CSDN上大牛的代码和思路，谢谢谢谢。
