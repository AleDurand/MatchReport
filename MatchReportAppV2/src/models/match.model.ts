import { alias } from '../decorators/alias.decorator';
import { ModelFactory } from './model-factory.model';
import { MatchStatus } from './match-status.model';

export class Match extends ModelFactory {
	@alias('id') id: number;
	@alias('date', Date) date: Date;
	@alias('stadium') stadium: string;
	@alias('local') localTeam: string;
	@alias('visitor') visitorTeam: string;
	@alias('status') status: MatchStatus;
	
}
