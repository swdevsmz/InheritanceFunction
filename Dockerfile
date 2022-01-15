#FROM public.ecr.aws/lambda/java:11
#
## Copy function code
#COPY target/FunctionDemo-0.0.1-SNAPSHOT-aws.jar ${LAMBDA_TASK_ROOT}
##RUN ls -al
#RUN jar -xvf FunctionDemo-0.0.1-SNAPSHOT-aws.jar
## Set the CMD to your handler (could also be done as a parameter override outside of the Dockerfile)
#CMD ["org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest"]

FROM public.ecr.aws/sam/build-java11:latest as build-image

WORKDIR "/task"
COPY src/ src/
COPY pom.xml ./

RUN mvn -q clean package
RUN mvn dependency:copy-dependencies -DincludeScope=compile

# https://github.com/aws/aws-lambda-base-images/blob/java11/Dockerfile.java11
FROM public.ecr.aws/lambda/java:11

#COPY --from=build-image /task/target/FunctionDemo-0.0.1-SNAPSHOT-aws.jar ${LAMBDA_TASK_ROOT}
COPY --from=build-image /task/target/classes /var/task/
COPY --from=build-image /task/target/dependency /var/task/lib
#RUN jar -xvf FunctionDemo-0.0.1-SNAPSHOT-aws.jar
# Command can be overwritten by providing a different command in the template directly.
# https://www.creationline.com/lab/39662
CMD ["org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest"]