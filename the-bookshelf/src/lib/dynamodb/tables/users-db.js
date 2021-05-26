const {docClient} = require("../dynamo-client.js");
const uuid = require("uuid");

// Get the DynamoDB table name from environment variables
const usersTable = 'the-bookshelf-user';

const createUser = async (data) => {
  const params = {
    TableName: usersTable,
    Item: {
      id: uuid.v1(),
      nick: data.nick,
      email: data.email,
    },
  };
  const user = await docClient.put(params).promise();
  return user.Item;
};

const deleteUser = async (id) => {
  const params = {
    TableName: usersTable,
    Key: {
      id,
    },
    ReturnValues: "ALL_OLD",
  };

  const user = await docClient.delete(params).promise();
  return user.Attributes;
};

const getAllUsers = async () => {
  const params = {
    TableName: usersTable,
  };

  const users = await docClient.scan(params).promise();
  return users.Items;
};

const getUserById = async (id) => {
  const params = {
    TableName: usersTable,
    Key: {
      id,
    },
  };
  const user = await docClient.get(params).promise();
  return user.Attributes;
};

const getUserByNick = async (nick) => {
  const params = {
    TableName: usersTable,
    FilterExpression: "nick = :nick",
    ExpressionAttributeValues: {
      ":nick": nick,
    },
  };

  const user = await docClient.scan(params).promise();
  return user.Attributes;
};

const updateUser = async (data) => {
  const params = {
    TableName: usersTable,
    Key: {
      id: data.id,
    },
    UpdateExpression: "set email = :e",
    ExpressionAttributeValues: {
      ":e": email,
    },
    ReturnValues: "ALL_NEW",
  };

  const user = await docClient.update(params).promise();
  return user.Attributes;
};

module.exports = {
  createUser,
  deleteUser,
  getAllUsers,
  getUserById,
  getUserByNick,
  updateUser
};
