FROM liquibase/liquibase:latest-alpine

ENV SERVER_URL=jdbc:postgresql://localhost:5433/matcher
ENV SERVER_USER_OWNER=matcher
ENV SERVER_PASS_OWNER=matcher
ENV CHANGELOG_FILE=changelog.xml

ADD . /liquibase/changelog

CMD ["sh", "-c", "docker-entrypoint.sh --url=${SERVER_URL} --username=${SERVER_USER_OWNER} --password=${SERVER_PASS_OWNER} --classpath=/liquibase/changelog --changeLogFile=${CHANGELOG_FILE} --log-level DEBUG update"]
