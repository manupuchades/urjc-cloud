// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/users-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const id = event.pathParameters.id;

  try {
    const user = await dbManager.deleteUser(id);

    if (user) {
      return {
        statusCode: 200,
        body: JSON.stringify(user),
      };
    } else {
      return {
        statusCode: 404,
        body: { error: "User not found" },
      };
    }
  } catch (err) {
    return {
      statusCode: 400,
      body: { error: "Invalid user id" },
    };
  }
};
