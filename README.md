Git
-------------------------------
cd HintMgrApp
git init
git remote add origin git@github.com:louising/HintMgrApp.git
git checkout -b dev

http://localhost:8080/hintmgr/swagger-ui.html
http://localhost:8080/SpringBootDemo/dummy/sysInfo

Deploy Note
-------------------------------
1. configPath: D:/workspace/workspace_ee/HintMgrApp/docs/
2. Upload to configPath: city.js, HintTemplate.xlsx

cd /home/ubuntu
sudo su

ps -ef | grep java | grep -v 'grep' | awk '{print $2}'  | xargs kill -9
rm hintmgr.jar
nohup java -jar hintmgr.jar &

Restart(sh restart.sh)
-----------------------
ps -ef | grep java | grep -v 'grep' | awk '{print $2}'  | xargs kill -9
nohup java -jar hintmgr.jar &

Redeploy
---------------------
ps -ef | grep java | grep -v 'grep' | awk '{print $2}'  | xargs kill -9
rm hintmgr.jar
mv hintmgr2.jar hintmgr.jar

nohup java -jar hintmgr.jar &


cd /home/ubuntu
sudo su

ps -ef | grep java | grep -v 'grep' | awk '{print $2}'  | xargs kill -9
rm hintmgr.jar
nohup java -jar hintmgr.jar &

Restart(sh restart.sh)
-----------------------
ps -ef | grep java | grep -v 'grep' | awk '{print $2}'  | xargs kill -9
nohup java -jar hintmgr.jar &

Redeploy
---------------------
ps -ef | grep java | grep -v 'grep' | awk '{print $2}'  | xargs kill -9
rm hintmgr.jar
mv hintmgr2.jar hintmgr.jar

nohup java -jar hintmgr.jar &

Deploy Manual
==================================================================================================
1) Prepare files:
hintmgr.jar
application.yml
/home/ubuntu/cert/6562146_bex.rainchapter.com.pem;
/home/ubuntu/cert/6562146_bex.rainchapter.com.key;

2) Config application.yml
* Port
port: 8080

* DB
spring:
    datasource:
        url: jdbc:mysql://localhost:3306/pim?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&useSSL=false
        username: hintmgr
        password: "xxx"

* Path, MiniAPP
appConf:
    //Put files here: city.js, HintTemplate.xlsx  
    configPath: /home/ubuntu/
    
    rootPwd: xxx
    appId: xxx
    appSecret: xxx

* OSS
oss:
    endpoint: "https://oss-cn-guangzhou.aliyuncs.com"
    accessKey: "xxx"
    secretKey: "xxx"
    bucket: "sw-clue"

3) Start Java Backend RESTful Service
nohup java -Xmx1g -jar hintmgr.jar &

4) Start Nginx (Note: the first line: user xxx)
* /etc/nginx/nginx.conf

root /home/ubuntu/web/dist;

location /HintMgrAPI/ {
    proxy_pass http://127.0.0.1:8080/;
    proxy_connect_timeout 600;
    proxy_send_timeout 600;
    proxy_read_timeout 600;
}

server_name bex.xxx.com;
ssl on;
ssl_certificate     /home/ubuntu/cert/6562146_bex.xxx.com.pem;
ssl_certificate_key /home/ubuntu/cert/6562146_bex.xxx.com.key;

* Start
./nginx

Install Nginx
------
apt install nginx
vim /etc/nginx/nginx.conf
/home/ubuntu/web
chown ubuntu:ubuntu

Install JDK
------
apt list | grep openjdk-8
apt list | grep mysql

apt install openjdk-8-jre-headless

Install MySQL
------
apt install mysql-server-5.7
mysql_secure_installation
root root

systemctl status mysql.service
systemctl restart mysql.service
mysql -uroot -p
grant all privileges on *.* to root@'%' identified by 'root';
grant all privileges on *.* to hintmgr@'%' identified by 'xxx';
CREATE DATABASE pim DEFAULT CHARACTER SET utf8mb4;

systemctl status nginx.service
nginx -t


wget 119.91.195.217:3306
telnet 119.91.195.217:3306

Install JDK on CentOS 7.9
------
cat /etc/redhat-release
CentOS Linux release 7.9.2009 

Check
rpm -qa |grep java
rpm -qa |grep jdk
rpm -qa |grep gcj

If has, then uninstall: 
rpm -qa | grep java | xargs rpm -e --nodeps 

yum list | grep java-1.8
yum list | grep mysql

yum install java-1.8.0-openjdk.x86_64
yum install java-1.8.0-openjdk* -y
// -y, --assumeyes  //Assume yes; assume that the answer to any question which would be asked is yes.

Installed: java-1.8.0-openjdk.x86_64 1:1.8.0.322.b06-1.el7_9

Install MySQL on CentOS 7.9
------
wget http://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm

yum -y install mysql57-community-release-el7-11.noarch.rpm

yum repolist enabled | grep mysql.*

yum install mysql-community-server -y

QA
------
The GPG keys listed for the "MySQL 5.7 Community Server" repository are already installed but they are not correct for this package.

Q:
systemctl start mysqld.service
Failed to start mysqld.service: Unit not found.

A:
rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022
yum install -y mysql-server

systemctl start mysqld.service
systemctl enable mysqld.service   //Add to start when launch (boot)

See initial password for root:
grep "password" /var/log/mysqld.log

A temporary password is generated for root@localhost: /G*ocDrj=2te
mysql -uroot -p

ALTER USER 'root'@'localhost' IDENTIFIED BY 'xxx';

show databases;

CREATE DATABASE pim DEFAULT CHARACTER SET utf8mb4;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` char(32) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  `role_authories` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Records of sys_role
INSERT INTO `sys_role` VALUES ('1bb60038e24c46a6aaa3be93cce14807', 'Role0', 'Role Desc0', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\"]', '2021-12-06 19:45:11');

http://114.55.8.6:8080/hintmgr/dummy/sysInfo

Test
====================================
ADMIN 
    Account1    AOKZU0WODt/lwFsWlKNh932lhfKAehHDgIrX8Q9kI3XySzOGnekyecnmF+uln+ptFY+jmPA/J1pSvMJcAiQ9wimh/c/GqcA7+sy3ouC515I=

??????????????? [userId=area01, rolePosition=1]  AOKZU0WODt/lwFsWlKNh971Tha/GmsDTKgaYlxAmkqXS80wYh5WnTZsT9hGS8/m7
                   area02                   AOKZU0WODt/lwFsWlKNh971Tha/GmsDTKgaYlxAmkqXdExsHqVV3BbAoYdrVFZid

??????????????? [userId=proj01, rolePosition=2]  AOKZU0WODt/lwFsWlKNh93OI/5riAQcBlPn2dmdU4JUZzttjkyBw/B34mjcG/3Ph
                   pr0j02                   AOKZU0WODt/lwFsWlKNh93OI/5riAQcBlPn2dmdU4JXTyv/ZOLESLTi2j8EPF8b1

?????????     [userId=sale01, rolePosition=3]  AOKZU0WODt/lwFsWlKNh98i4oSVjc1eK4NqL8ZOib4JVHpEdAujN3p/DObTUOFkx
                   sale02                   AOKZU0WODt/lwFsWlKNh98i4oSVjc1eK4NqL8ZOib4LQhcPtYkGTO8Nkvv2Hvggf
                   sale03                   AOKZU0WODt/lwFsWlKNh98i4oSVjc1eK4NqL8ZOib4J9eRUAoXwK+m7odcYjpmss
?????????     [userId=build01, rolePosition=4] AOKZU0WODt/lwFsWlKNh9xXYltiEbIiNtr/QFWvidEsMvuap+zeM4GwBM9nxR99q
                   build02                  AOKZU0WODt/lwFsWlKNh9xXYltiEbIiNtr/QFWvidEuwFKO3fZZRnTTS6O9LcgPn
                   
                   
Role
-----------------------------------------------------------------------
????????????1
    ????????????1
        ?????????1
        ?????????2
        ?????????3
        ?????????1
    ????????????2
        ?????????4
        ?????????5
        ?????????2
????????????2
    ????????????3
        ?????????6
        ?????????3

Case1: ?????????->?????????->?????? (??????-?????????)
-----------------------------------------------------------------------
1. ??????????????????????????????:null ???????????????:null
2. ????????????(HintLevel)
3. ???????????????(??????, ??????)
4. ???????????????????????????(????????????, ??????) ==> ???????????????,???????????????,??????
5. ????????????(?????? ???????????????/???????????????)

Case2: Public-Hint-Pool(project, level, audit_status=1, user_id = null)
-----------------------------------------------------------------------
????????????
    GET /app/user/publicHints Public-Hint-Pool
    GET /app/user/myHints My-Hint
    GET /app/user/freeHint Free-Hint
    GET /app/user/askHint Ask-Hint

??????
--------
sale01(??????1?????????1, HintLevel1) ?????? ????????? ????????????3???  
sale02(??????1?????????1, HintLevel1) ?????? ????????? ????????????4???

Hint 16???
--------
Hint10 (project01,level01,audit_status=1) ??????1 ??????1 ????????? 10??? 
Hint2(project01, level02)                 ??????1 ??????2 ????????? 2???
Hint1(project01, level=1, audit_status=0) ??????1 ??????1 ????????? 1???
Hint1(project01, level=1, audit_status=2) ??????1 ??????1   ?????? 1???
Hint2(project02, level=1, audit_status=1) ??????2 ??????1   ?????? 2???

1. ?????? My-Hint, Public-Hint-Pool   
    sale01(??????1?????????1, HintLevel1 ???????????? 3): Public-Hint-Pool 3(??????1?????????1) 
    sale02(??????1?????????1, HintLevel1 ???????????? 4): Public-Hint-Pool 3(??????1?????????1) 
2. sale01 ????????????Hint
3. ?????? My-Hint, Public-Hint-Pool 
    sale01 (??????1?????????1 ????????????2) Public-Hint-Pool 4 
    sale02 (??????1?????????1 ????????????4) Public-Hint-Pool 4
4. sale02 ????????????Hint
5. ?????? My-Hint, Public-Hint-Pool 
    sale01(????????????2 ) Public-Hint-Pool 3
    sale02(????????????5 ) Public-Hint-Pool 3

It demonstrates these features
-------------------------------    
1) Expose RESTful web service by Spring Boot through annotation.
2) DAO by MyBatis(CRUD, paging query, transaction)
3) Exception handler
4) I18N
5) XRSF defense(the simplest way:)
6) CORS(Cross-origin resource sharing)
7) jUnit test
8) Swagger //http://localhost:8080/SpringBootDemo/swagger-ui.html
9) Slf4j + Logback
A) Schedule
B) Encryption/Decryption
C) Session management(just for simple demo, in product environment should use SSL and encrypted cookie, or SSO)
D) Support static content, by default read the directory:  /static (or /public or /resources or /META-INF/resources) in the classpath;
   Remember: pom.xml/resources/resource/includes/<include>**/*.*</include> 

This demo application is arranged in 3 lays
1) Dao        //Access DB
2) Service    //Business logic
3) Controller //Expose service as Restful web service, permisson control, XRSF check
        
Usage
-------------------------------
1) Configuration
    A) application.yml 
       Config context, port, datasource, configuration directory
       
    B) logback.xml
       Output log(console, file, DB...)
       
2) Dao 
   e.g. DummyDao.java, DummyDao.xml
   
3) Service
   e.g. DummyServiceImpl.java 
   
4) Controller
   e.g. DummyController

5) Run
//Start DB (H2 DB)
c:\>java -jar "H:/lib/java/h2-1.4.197.jar"   
URL: jdbc:h2:tcp://localhost/~/H2DB-SpringRestDemo
user: sa
password: sa
           
DROP TABLE if exists t_user;
CREATE TABLE t_user(
    user_id INT IDENTITY,
    user_name VARCHAR(20), 
    login_name VARCHAR(20), 
    email VARCHAR(30), 
    birth_date DATE DEFAULT CURRENT_DATE,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
           
//DemoApplication.java ==> Run as Java Application
...
Started DemoApplication in 9.972 seconds (JVM running for 10.838) 
       
6) Access RESTful web services in browser, JS, PostMan or other client sides.
API DOC: http://localhost:8080/SpringBootDemo/swagger-ui.html
   
GET http://localhost:8080/SpringBootDemo/dummy/sysInfo
GET http://localhost:8080/SpringBootDemo/dummy/list
//Paging query: /dummy/page/{pageSize}/{pageIndex}
POST http://localhost:8080/SpringBootDemo/dummy/page/3/1  BODY: { "userId": 2, "userName": "Alice"}
                   
Note
-------------------------------
1. DAO
   1) Config datasource in application.yml/spring/datasource
   2) DataSourceConf.java/@MapperScan("com.zero.demo.dao")
   3) Write com.zero.demo.dao/xxxDao.java,xxxDao.xml
   4) Call Dao in DummyServiceImpl.java
        @Autowired
        private DummyDao dummyDao;              

2. Transaction
   @Transactional
   DummyServiceImpl.addDummy() {
      ...
   }
   
3. Expose RESTful web service
   1) class DummyController extends BaseController
   2) Add @RestController, @RequestMapping

4. Exception handler in BaseController.java
   1) DummyServiceImpl.java/addDummy()
      ... 
      throw new ServiceException("USER_ID_INVALID"); //Extract constant          
   2) Define i18n message in messages_*.properties for message_code of "USER_ID_INVALID"
   3) The controller will process the exception and return error message
   
5. I18N
   1) Set request header or url parameter: "languageToken=en-US" //or zh-CN
   2) i18n/messages_en_US.properties
          COUNTRY=The country is China
          ADDRESS=The address is province {0} city {1} Area {2}
   3) TestServiceImpl.java
          String country = ResourceUtil.getMessage("COUNTRY"); 
          String address = ResourceUtil.getMessage("ADDRESS", "Guangdong", "ShenZhen", "South Hill");
              
6. XRSF
   1) After login, invoke "GET /dummy/getToken" to get token
   2) Each time request except login/logout,  add the header of "token"   

7. CORS
   FilterConf.java/corsFilter()

8. jUnit test
   Single Unit Test: DummyServiceATest, DummyServiceBTest
   Run all unit tests : AllTest.java
   
9. Swagger (API doc)
   Swagger2Conf.java
   Access at: http://localhost:8080/SpringBootDemo/swagger-ui.html
   
A. Slf4j + Logback
   logback.xml
   
B. Schedule
   CountJobConf.java
    
C. Encryption/Decryption
   SecurityUtil.java
    
D. Session management(just for simple demo, in product environment should use SSL and encrypted cookie, or SSO)
   SessionManager.java

