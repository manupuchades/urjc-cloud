// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/users-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const id = event.pathParameters.id;
  const data = JSON.parse(event.body);
  data.id = id;

  try {
    const user = await dbManager.updateUser(data);

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
      body: JSON.stringify(err),
    };
  }
};
