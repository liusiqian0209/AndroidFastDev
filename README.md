# AndroidFastDev

一个Android网络应用的基础框架，便于快速开发<p>
A Foundamental Android Application Framework for Fast Development
<p><p>
##特性
### 基于网络请求最简化的原则，实现一行代码请求接口，任意位置可以接收结果。
> 封装Http网络请求，允许自动添加默认参数，支持依赖访问，支持上传和下载。<p>
> 通过Api名索引一个请求的全部基本信息(域名前缀，相对域名，参数，请求方式，返回的json字符串对应的对象)。<p>
> 封装了SharedPreference 和 Database 数据存储，方便数据的本地化。<p>

<p>
##使用说明
###全局
>1、所有使用@ExampleTag标注的代码均为示例代码,使用时根据实际需要修改或者删除它们(ExampleTag.java这个类亦可删除)<p>
>2、AppConstant类中存放全局控制变量<p>

###Http
>3、在BaseModel类中定义服务器返回json串的公共字段,在model包下定义每个接口返回的格式<p>
>4、Api类中定义接口<p>
>5、DomainManager中定义每个接口的前缀域名<p>
>6、ExecutorWrapper中定义需要依赖访问的接口 以及 请求中需要默认添加的参数(已经用TODO指明位置)<p>
>7、HttpService中处理请求失败的情况(使用时可根据需要修改这个类)<p>
>8、重写BaseActivity和BaseFragment中三个protected声明的方法处理请求成功的情况(使用时可根据需要修改这个类)<p>
>9、Requests类中定义接口请求的快捷方法<p>

###数据本地存储
>######SharedPreference
>10、在GlobalConfig中定义SharedPreference中要保存的不区分用户的信息,在UserConfig中定义要保存的每个用户的配置信息<p>
>######DataBase
>11、database.bean包下定义数据库每张表的字段名<p>
>12、database.dao包下定义访问数据表的快捷方法<p>
>13、所有的数据表添加到DatabaseHelper类中的tableCls成员的数组中<p>

