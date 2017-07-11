import { alias } from '../decorators/alias.decorator';

import { ModelFactory } from './model-factory.model';

export class HttpError extends ModelFactory {
  @alias('status') status: number;
  @alias('code') code: string;
  @alias('message') message: string;
}
