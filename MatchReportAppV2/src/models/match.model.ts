import { MatchStatus } from './match-status.model';

export class Match {
	id: number;
	date: Date;
	stadium: string;
	localTeam: string;
	visitorTeam: string;
	status: MatchStatus;
	
	constructor(args: any = null) {
		for (var key in args) {
			this[key] = args[key];
		}
	}
}
