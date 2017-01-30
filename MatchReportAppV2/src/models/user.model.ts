export class User {
	name: string;
  gender: string;
  picture: string;
  email: string;

	constructor(args: any = null) {
		for (var key in args) {
			this[key] = args[key];
		}
	}
}