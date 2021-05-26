// Create clients and set shared const values outside of the handler.
const dbManager = require("../../lib/dynamodb/tables/comments-db");

exports.handler = async (event) => {
  console.info("received:", event);

  const id = event.pathParameters.commentId;

  try {
  const comment = await dbManager.deleteCommentInBook(id);

  if (comment) {
    return {
        statusCode: 200,
        body: JSON.stringify(data),
    };
} else {
    return {
      statusCode: 404,
      body: { error: "Comment not found" },
    };
  }
} catch (err) {
  return {
    statusCode: 400,
    body: { error: "Invalid comment id" },
  };
}
};
