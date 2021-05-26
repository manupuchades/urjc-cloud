// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/comments-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const id = event.pathParameters.id;

  const data = await dbManager.getAllCommentsByUser(id);

  return {
    statusCode: 200,
    body: JSON.stringify(data),
  };
};
