const AWS = require('aws-sdk');

console.info(">>>> dynamo endpoint: ",process.env.AWS_DYNAMO_ENDPOINT);

AWS.config.update({
  endpoint: process.env.AWS_DYNAMO_ENDPOINT || 'https://dynamodb.eu-west-1.amazonaws.com',
  region: 'eu-west-1'
});

const docClient = new AWS.DynamoDB.DocumentClient();

module.exports = {
  docClient
};