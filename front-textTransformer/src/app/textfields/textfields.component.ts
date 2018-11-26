import { Component, OnInit } from '@angular/core';
import { TextService } from '../text.service';

@Component({
  selector: 'app-textfields',
  templateUrl: './textfields.component.html',
  styleUrls: ['./textfields.component.css']
})
export class TextfieldsComponent implements OnInit {

  constructor(private textService: TextService) { }

  ngOnInit() {
    this.getOutputText();
  }

  getOutputText(): void {
    //this.outputText = this.textService.getOutputText(); //call this in a function that responds on click instead of ngOnInit
  
    this.textService.getOutputText().subscribe(outputText => this.outputText = outputText)
  }

  inputText: string;
  outputText: string;



  // request wyglada tak: http://localhost:8080/TextTransformer/transform?text=aaa&function=upper
}
