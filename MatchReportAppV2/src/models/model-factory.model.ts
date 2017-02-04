export class ModelFactory {

  constructor(args: any = null) {
    var fields = (<any> this.constructor)._alias;
    for (var field in fields) {
      var alias = fields[field]['alias'];
      if(!this.isNullOrUndefined(args[alias])) {
        if(this.isPrimitiveOrPrimitiveClass(args[alias])) {      
          this[field] = args[alias];
        } else {
          var type = fields[field]['type'];
          if(this.isArrayOrArrayClass(args[alias])) {
            var array = this.deserializeArray(args[alias], type, alias);
            this[field] = array;
          } else {
            if(this.isNullOrUndefined(type)) {
              throw new Error('Alias decorator => Type is undefined for: Class: ' + this.constructor.name + ' - Property: ' + alias + '.');
            } else {
              this[field] = new type(args[alias]);  
            }
          }
        }
      } else {
        this[field] = args[field];
      }      
    }
  }

  private deserializeArray(args: Array<any>, type: any, alias: string): Array<any> {
    let toReturn = [];
    if(this.isArrayOrArrayClass(args)) {
      args.forEach((argn) => {
        if(this.isArrayOrArrayClass(argn)){
          toReturn.push(this.deserializeArray(argn, type, alias));
        } else {
          if(this.isPrimitiveOrPrimitiveClass(argn)) {
            toReturn.push(argn);
          } else {
            if(this.isNullOrUndefined(type)) {
              throw new Error('Alias decorator => Type is undefined for: Class: ' + this.constructor.name + ' - Property: ' + alias + '.');
            } else {
              toReturn.push(new type(argn))  ;  
            }            
          }          
        }
      });
    } else {
      throw new Error('Alias decorator => Cannot use deserializeArray() using an object as parameter.')
    }
    return toReturn;
  }

  private isPrimitiveOrPrimitiveClass(obj: any): boolean {
    return !!(['string', 'boolean', 'number'].indexOf((typeof obj)) > -1 || (obj instanceof String || obj === String ||
    obj instanceof Number || obj === Number ||
    obj instanceof Boolean || obj === Boolean));
  }

  private isArrayOrArrayClass(obj: any): boolean {
    if (obj instanceof Array) {
        return true;
    }
    return Object.prototype.toString.call(obj) === '[object Array]'
  }

  private isNullOrUndefined(obj: any): boolean {
    return obj === null || obj === undefined;
  }

}