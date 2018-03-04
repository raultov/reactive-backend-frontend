import { Component, ChangeDetectionStrategy } from '@angular/core';
import { AppService } from './app.service';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/observable/empty';
import 'rxjs/add/operator/share';

import { Book } from './app.service';

@Component({
  selector: 'app-root',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  result$: Observable<Array<Book>>;

  constructor(private appService: AppService) {
  }

  getRecommended() {
    this.result$ = this.appService.getRecommendedReactive();
  }
}
