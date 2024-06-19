# 기존 Tomcat 이미지를 베이스로 함
FROM tomcat:9.0

RUN cp -R webapps.dist/* webapps

# 웹 애플리케이션을 복사
COPY ./webapp /usr/local/tomcat/webapps/ROOT

# 필요한 디렉터리 생성
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# 서블릿 및 JDBC 드라이버 복사
COPY ./webapp/BoardServlet.java /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/BoardServlet.java
COPY ./webapp/WEB-INF/lib/mysql-connector-java-8.4.0.jar /usr/local/tomcat/lib/mysql-connector-java-8.4.0.jar

# 서블릿 컴파일
RUN javac -cp "/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/lib/mysql-connector-java-8.4.0.jar" \
    /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/BoardServlet.java

# 컴파일 완료 후 원본 .java 파일 삭제
RUN rm /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/BoardServlet.java

# 포트 8080 노출
EXPOSE 8080

