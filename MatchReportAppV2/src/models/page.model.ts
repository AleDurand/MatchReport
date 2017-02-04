export class Page {
  title: string;
  component: any;
  icon: string;
  logsOut?: boolean;

	constructor(args: any = null) {
		for (var key in args) {
			this[key] = args[key];
		}
	}

}