const {docClient} = require("../dynamo-client.js");
const uuid = require("uuid");

// Get the DynamoDB table name from environment variables
const commentsTable = 'the-bookshelf-comment';

const createCommentInBook = async (data) => {
  const params = {
    TableName: commentsTable,
    Item: {
      id: uuid.v1(),
      userId: data.userId,
      bookid: data.bookId,
      comment: data.comment,
      score: data.score,
    },
  };
  const comment = await docClient.put(params).promise();
  return comment.Item;
};

const deleteCommentInBook = async (id) => {
  const params = {
    TableName: commentsTable,
    Key: {
      id,
    },
    ReturnValues: "ALL_OLD",
  };

  const comment = await docClient.delete(params).promise();
  return comment.Attributes;
};

const getAllCommentsByUser = async (userId) => {
  const params = {
    TableName: commentsTable,
    FilterExpression: "userId = :userId",
    ExpressionAttributeValues: {
      "userId": userId,
    },
  };

  const comments = await docClient.scan(params).promise();
  return comments.Items;
};

module.exports = {
  createCommentInBook,
  deleteCommentInBook,
  getAllCommentsByUser,
};
