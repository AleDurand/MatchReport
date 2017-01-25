export class Club {
	id: number;
	name: string;
	address: string;
	image: string;
	url: string;

	constructor(args: any = null) {
		for (var key in args) {
			this[key] = args[key];
		}
	}
}