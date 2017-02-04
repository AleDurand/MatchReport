import { alias } from '../decorators/alias.decorator';
import { ModelFactory } from './model-factory.model';

export class User extends ModelFactory {
	@alias('firstname') firstname: string;
	@alias('lastname') lastname: string;
  @alias('gender') gender: string;
  @alias('picture') picture: string;
  @alias('email') email: string;

}