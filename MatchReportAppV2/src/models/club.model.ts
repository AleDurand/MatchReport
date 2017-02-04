import { alias } from '../decorators/alias.decorator';
import { ModelFactory } from './model-factory.model';

export class Club extends ModelFactory {
	@alias('id') id: number;
	@alias('name') name: string;
	@alias('address') address: string;
	@alias('image') image: string;
	@alias('url') url: string;

}