aws cloudformation deploy\
--stack-name $APPLICATION_NAME\
--template-file ./ECSService.yml\
--parameter-overrides\
  ApplicationName=$APPLICATION_NAME\
  ECRepositoryUri=$AWS_ID/$APPLICATION_REPOSITORY:$COMMIT_HASH\
  DBUsername=$DB_USERNAME\
  DBPassword=$DB_PASSWORD\
  SubnetID=$SUBNET_ID\
  SecurityGroupID=$SECURITY_GROUP_ID\
  TargetGroupARN=$UTOPIA_PASSENGERMS_TARGETGROUP\
--capabilities \"CAPABILITY_IAM\" \"CAPABILITY_NAMED_IAM\"