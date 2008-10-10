package org.seasar.s2csv.array;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * 自動伸張リスト
 * </p>
 * this.get(int)で現在のリストの要素数より大きなindexが指定された場合、 <br>
 * このListはそのindexまでデフォルト値で初期化された要素を追加し、 <br>
 * 新しく追加された要素を返す <br>
 * dafaultValueに指定する値はpublicなコピーコンストラクタを実装していなければならない
 *
 * @param <E> AutoExpandListのインスタンスを型付けする型変数<br>
 *
 * @author newta
 */

public final class AutoExpandList<E> extends ArrayList<E> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see java.util.ArrayList
	 */
	public AutoExpandList() {
		super();
	}

	/**
	 * @param c c
	 * @see java.util.ArrayList
	 */
	public AutoExpandList(Collection<? extends E> c) {
		super(c);
	}

	/**
	 * @param initialCapacity initialCapacity
	 * @see java.util.ArrayList
	 */
	public AutoExpandList(int initialCapacity) {
		super(initialCapacity);
	}

//	/**
//	 * @see java.util.List#get(int)
//	 */
//	@Override
//	public E get(final int index) {
//		if (index < 0) {
//			return null;
//		}
//		if (this.size() <= index) {
//			this.add(index, this.getDefaultValue());
//		}
//
//		return super.get(index);
//	}

	/**
	 * @see java.util.List#set(int, Object)
	 */
	@Override
	public E set(final int index, final E obj) {
		try {
			for (; this.size() <= index;) {
				this.add(this.getDefaultValue());
			}
		} catch (Exception ex) {
			throw new IllegalStateException(ex.toString());
		}

		return super.set(index, obj);
	}

	/**
	 * @return デフォルト値
	 */
	public E getDefaultValue() {
		return null;
	}

	/**
	 *
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(final int index, final E element) {
		if (super.size() <= index) {
			try {
				for (; this.size() < index;) {
					super.add(this.getDefaultValue());
				}
			} catch (Exception ex) {
				throw new IllegalStateException(ex.toString());
			}
		}
		super.add(index, element);
	}
}