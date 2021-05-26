const dbManager = require("../../lib/dynamodb/tables/comments-db");
const usersDbManager = require("../../lib/dynamodb/tables/users-db");


exports.handler = async (event) => {
  console.info("received:", event);

  const data = JSON.parse(event.body);

  const user = await usersDbManager.getUserByNick(data.nick);
  data.userId = user.id;

  try {
    const comment = await dbManager.createCommentInBook(data);
    return buildResponse(201, data);
  } catch (err) {
    return {
      statusCode: 400,
      body: JSON.stringify(err),
    };
  }
};

function buildResponse(statusCode, body) {
  return {
    statusCode,
    body: JSON.stringify(body),
  };
}
