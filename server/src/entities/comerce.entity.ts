
import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Category {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({nullable: true})
  title: string;

  @Column()
  description: string;

  @Column()
  category: string;


  @Column()
  city: string;

}
