FROM liquibase/liquibase:latest-alpine

ENV MSSQL_SERVER_URL="jdbc:postgresql://localhost:5432/bankservice"
ENV MSSQL_SERVER_USER_OWNER="bankservice"
ENV MSSQL_SERVER_PASS_OWNER="bankservice"
ENV CHANGELOG_FILE=changelog.xml

ADD . /liquibase/changelog

CMD ["sh", "-c", "docker-entrypoint.sh --url=${MSSQL_SERVER_URL} --username=${MSSQL_SERVER_USER_OWNER} --password=${MSSQL_SERVER_PASS_OWNER} --classpath=/liquibase/changelog --changeLogFile=${CHANGELOG_FILE} --log-level DEBUG update"]
