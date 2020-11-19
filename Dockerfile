FROM marquespv/springboot

COPY . /usr/local/

CMD ["cd", "/usr/local/challenge"]

CMD [ "mvn", "package" ]

# Nome do pacote gerado pelo Maven
ARG JAR_FILE=target/challenge-0.0.1-SNAPSHOT.jar
 
# Altera internamente para o diretório /opt/app
WORKDIR /opt/app
 
# Copia a aplicação com um nome diferente para WORKDIR 
cp ${JAR_FILE} app.jar
 
# Executa o comando java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]


