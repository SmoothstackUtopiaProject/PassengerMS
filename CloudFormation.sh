aws cloudformation deploy \
--stack-name $APPLICATION_NAME \
--template-file ./ECSService.yml \
--parameter-overrides \
ApplicationName=$APPLICATION_NAME \
ECRepositoryUri=$AWS_ID/$APPLICATION_REPOSITORY:$COMMIT_HASH \
ExecutionRoleArn=${EXECUTION_ROLE_ARN} \
DBUrl=$DB_URL \
DBUsername=$DB_USERNAME \
DBPassword=$DB_PASSWORD \
SubnetID=$SUBNET_ID \
SecurityGroupID=$SECURITY_GROUP_ID \
TargetGroupARN=$UTOPIA_PASSENGERMS_TARGETGROUP \
VpcId=${VPC_ID} \
--capabilities "CAPABILITY_IAM" "CAPABILITY_NAMED_IAM"