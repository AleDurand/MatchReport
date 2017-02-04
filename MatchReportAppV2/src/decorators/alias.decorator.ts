export function alias<T>(alias: string, type?: T) {
  return function (target: Object, propertyKey: string | symbol) {
    target['constructor']['_alias'] = target['constructor']['_alias'] || {};
    target['constructor']['_alias'][propertyKey] = target['constructor']['_alias'][propertyKey] || {};
    target['constructor']['_alias'][propertyKey]['alias'] = alias;
    target['constructor']['_alias'][propertyKey]['type'] = type;
  }
}
