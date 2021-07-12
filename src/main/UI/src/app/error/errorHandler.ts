import {Observable, of} from 'rxjs';

export function  handleError<T>(operation: string = 'operation', result?: T): any{
  return (error: any): Observable<T> => {
    console.error(operation);
    console.error(error);
    return of(result as T);
  };
}
