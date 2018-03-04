import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/of';

import * as EventSource from 'eventsource'

@Injectable()
export class AppService {

    books: Book[] = new Array();

    constructor(private http: HttpClient, private zone: NgZone) {
    }

    getRecommended(): Observable<Array<Book>> {
        this.books = new Array();
        return this.http.get('/my-service/recommended')
            .map(result => {
                this.books.push(result as Book);
                return this.books;
            })
            .catch(_ => Observable.of(this.books));
    }

    getRecommendedReactive(): Observable<Array<Book>> {
        this.books = new Array();
        return Observable.create((observer) => {
            let eventsource = new EventSource('/my-service/recommended');

            eventsource.onmessage = (event) => this.zone.run(() => {
                console.log('Received event: ', event);
                let book = JSON.parse(event.data);

                this.books.push(new Book(book['title']));
                observer.next(this.books);

                if (this.books.length >= 10) {
                    observer.complete();
                }
            });

            eventsource.onerror = (error) => observer.error(error.type);

            return () => {
                eventsource.close();
            }
        });
    }
}

export class Book {
    title: string;

    constructor( title: string) {
        this.title = title;
    }
}
