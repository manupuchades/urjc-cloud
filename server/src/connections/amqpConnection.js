import { connect } from 'amqplib';
import DebugLib from 'debug';
import configHandler from '../clients/plannerNotificationHandler.js';

const debug = new DebugLib('server:amqp');

export let amqpChannel;

export async function connectAmqp() {
  
  const URL = `amqp://${process.env.AMQP_USER}:${process.env.AMQP_PASS}@${process.env.AMQP_HOST}:${process.env.AMQP_PORT}`;

  const conn = await connect(URL);
  amqpChannel = await conn.createChannel();
  
  configHandler(amqpChannel);

  process.on('exit', () => {
    amqpChannel.close();
    debug(`Closing rabbitmq channel`);
  });
}
