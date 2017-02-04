import { alias } from '../decorators/alias.decorator';
import { ModelFactory } from './model-factory.model';

export class Event extends ModelFactory {
	@alias('id') id: number;
	@alias('name') name: string;
	@alias('team') team: string;
	@alias('desc') desc: string;
	@alias('minute') minute: number;
	@alias('extra-minute') extraMinute: number;
	@alias('type') type: string;

}
