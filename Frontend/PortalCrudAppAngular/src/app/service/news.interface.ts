export interface NewsDTO {
    title: String,
    article: String
}

export interface NewsResponseDTO {
  id: number;
  title: string;
  article: string;
  author: Author;
  createDate: CreateDate;
  lastEdit: CreateDate;
  commentSet: any[];
  imageSet: ImageSet[];
  newsRatings: string;
}

interface ImageSet {
  id: number;
  alt: string;
  link: string;
}

interface CreateDate {
  month: string;
  year: number;
  dayOfMonth: number;
  hour: number;
  minute: number;
  monthValue: number;
  nano: number;
  second: number;
  dayOfWeek: string;
  dayOfYear: number;
  chronology: Chronology;
}

interface Chronology {
  id: string;
  calendarType: string;
}

interface Author {
  username: string;
  role: string;
}


export interface CommentDTO {
  id: number;
  author: Author;
  commentText: string;
  createDate: CreateDate;
  lastEdit: CreateDate;
}




