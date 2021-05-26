const dbManager = require("../../lib/dynamodb/tables/users-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const data = JSON.parse(event.body);

  const isUserPresent = await dbManager.getUserByNick(data.nick);

  if (isUserPresent) {
    return {
      statusCode: 409,
      body: { error: "Already exists a user with that nick" },
    };
  }

  try {
    const user = await dbManager.createUser(data);
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
