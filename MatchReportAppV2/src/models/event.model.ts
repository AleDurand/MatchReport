export class Event {
	id: number;
	name: string;
	team: string;
	desc: string;
	minute: number;
	extraMinute: number;
	type:string;

	constructor(args: any = null) {
		for (var key in args) {
			this[key] = args[key];
		}
	}

}
